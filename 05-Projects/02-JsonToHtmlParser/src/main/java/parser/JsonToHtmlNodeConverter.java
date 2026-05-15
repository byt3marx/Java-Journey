package parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonToHtmlNodeConverter {

    private static final Set<String> VOID_ELEMENTS = Set.of("meta", "link", "img", "br", "hr", "input");

    public Map<String, Object> convertElement(String tag, Object value) {
        Map<String, Object> element = new LinkedHashMap<>();

        element.put("tag", tag);

        if (value instanceof String) {
            element.put("text", value);
            return element;
        }

        if ("meta".equals(tag)) {
            return convertMeta(value);
        }

        if (value instanceof Map) {
            Map<String, Object> rawMap = (Map<String, Object>) value;

            if (isVoidElement(tag) && containsOnlyPrimitiveValues(rawMap)) {
                element.put("attributes", convertAttributes(rawMap));

                return element;
            }
            List<Object> children = new ArrayList<>();

            for (Map.Entry<String, Object> entry : rawMap.entrySet()) {
                convertChildEntry(element, children, entry.getKey(), entry.getValue());
            }

            element.put("children", children);

            return element;
        }

        return element;
    }

    public Map<String, Object> convertDocument(Map<String, Object> rawJson) {

        Map<String, Object> htmlElement = new LinkedHashMap<>();
        htmlElement.put("tag", "html");

        List<Object> children = new ArrayList<>();

        if (rawJson.containsKey("language")) {
            Map<String, String> attributes = new LinkedHashMap<>();
            attributes.put("lang", (String) rawJson.get("language"));
            htmlElement.put("attributes", attributes);
        }

        if (rawJson.containsKey("head")) {
            children.add(convertElement("head", rawJson.get("head")));
        }

        if (rawJson.containsKey("body")) {
            children.add(convertElement("body", rawJson.get("body")));
        }

        htmlElement.put("children", children);

        return htmlElement;

    }

    private Map<String, Object> convertMeta(Object value) {
        Map<String, Object> metaElement = new LinkedHashMap<>();
        metaElement.put("tag", "meta");

        if (value instanceof Map) {
            Map<String, Object> rawMap = (Map<String, Object>) value;

            Map<String, String> attributes = new LinkedHashMap<>();

            for (Map.Entry<String, Object> entry : rawMap.entrySet()) {
                attributes.put(entry.getKey(), String.valueOf(entry.getValue()));
            }

            metaElement.put("attributes", attributes);
        }

        return metaElement;

    }

    private List<Object> convertMetaElements(Map<String, Object> rawMap) {
        List<Object> metaElements = new ArrayList<>();

        for (Map.Entry<String, Object> entry : rawMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if ("charset".equals(key)) {
                Map<String, String> attributes = new LinkedHashMap<>();

                attributes.put(key, String.valueOf(value));

                metaElements.add(createElementWithAttributes("meta", attributes));
            }

            if ("viewport".equals(key)) {
                Map<String, Object> viewportMap = (Map<String, Object>) value;
                StringBuilder content = new StringBuilder();
                int index = 0;

                for (Map.Entry<String, Object> viewportEntry : viewportMap.entrySet()) {
                    if (index > 0) {
                        content.append(", ");
                    }

                    content.append(viewportEntry.getKey())
                            .append("=")
                            .append(String.valueOf(viewportEntry.getValue()));

                    index++;
                }

                Map<String, String> attributes = new LinkedHashMap<>();

                attributes.put("name", "viewport");
                attributes.put("content", content.toString());

                metaElements.add(createElementWithAttributes("meta", attributes));
            }
        }

        return metaElements;
    }

    private Map<String, String> convertAttributes(Object value) {
        Map<String, String> attributes = new LinkedHashMap<>();

        if (value instanceof Map<?, ?> rawMap) {
            for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
                String key = String.valueOf(entry.getKey());
                Object attributeValue = entry.getValue();

                if (key.equals("style") && attributeValue instanceof Map<?, ?> styleMap) {
                    StringBuilder css = new StringBuilder();

                    for (Map.Entry<?, ?> styleEntry : styleMap.entrySet()) {
                        css.append(styleEntry.getKey())
                                .append(": ")
                                .append(styleEntry.getValue())
                                .append("; ");
                    }
                    attributes.put(key, css.toString().trim());
                }
                else {
                    attributes.put(key, String.valueOf(attributeValue));
                }
            }
        }
        return attributes;
    }

    private boolean containsOnlyPrimitiveValues(Map<String, Object> rawMap) {

        for (Object value : rawMap.values()) {
            if (value instanceof Map || value instanceof List) {
                return false;
            }
        }
        return true;
    }

    private boolean isVoidElement(String tag) {
        return VOID_ELEMENTS.contains(tag.toLowerCase());
    }

    private Map<String, Object> createElementWithAttributes(String tag, Map<String, String> attributes) {
        Map<String, Object> element = new LinkedHashMap<>();

        element.put("tag", tag);
        element.put("attributes", attributes);

        return element;
    }

    private void convertChildEntry(
            Map<String, Object> element,
            List<Object> children,
            String childTag,
            Object childValue
    ) {
        if ("meta".equals(childTag)) {
            Map<String, Object> metaMap = (Map<String, Object>) childValue;
            children.addAll(convertMetaElements(metaMap));
        }
        else if ("attributes".equals(childTag)) {
            element.put("attributes", convertAttributes(childValue));
        }
        else if (childValue instanceof List<?> list) {
            for (Object item : list) {
                children.add(convertElement(childTag, item));
            }
        }
        else {
            children.add(convertElement(childTag, childValue));
        }

    }

}
