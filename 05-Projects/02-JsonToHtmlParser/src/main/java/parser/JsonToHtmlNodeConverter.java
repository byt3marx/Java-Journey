package parser;

import model.HtmlNode;

import html.HtmlRules;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonToHtmlNodeConverter {

    public Map<String, Object> convertElement(String tag, Object value) {
        Map<String, Object> element = createElement(tag);

        if (value instanceof String) {
            element.put("text", value);
            return element;
        }

        if ("meta".equals(tag)) {
            return convertMeta(value);
        }

        if (value instanceof Map) {
            Map<String, Object> rawMap = (Map<String, Object>) value;

            if (isVoidElement(tag) && canBeTreatedAsAttributes(rawMap)) {
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

    public HtmlNode convertElementToNode(String tag, Object value) {
        HtmlNode element = new HtmlNode(tag);

        if (value instanceof String) {
            element.setText((String) value);
            return element;
        }

        if ("meta".equals(tag)) {
            return convertMetaToNode(value);
        }

        if (value instanceof Map<?, ?> rawMap) {

            if (isVoidElement(tag) && canBeTreatedAsAttributes(rawMap)) {
                Map<String, String> attributes = convertAttributes(rawMap);

                for (Map.Entry<String, String> attribute : attributes.entrySet()) {
                    element.addAttribute(attribute.getKey(), attribute.getValue());
                }
                return element;
            }

            for (Map.Entry<?, ?> entry : rawMap.entrySet()) {

                String childTag = String.valueOf(entry.getKey());
                Object childValue = entry.getValue();

                if ("attributes".equals(childTag)) {
                    Map<String, String> attributes = convertAttributes(childValue);

                    for (Map.Entry<String, String> attribute : attributes.entrySet()) {
                        element.addAttribute(
                                attribute.getKey(),
                                attribute.getValue());
                    }
                }
                else if ("meta".equals(childTag) && childValue instanceof Map<?, ?> metaMap) {
                    for (HtmlNode metaNode : expandMetaElementsToNode(metaMap)) {
                        element.addChild(metaNode);
                    }
                }
                else if (childValue instanceof List<?> list) {
                    for (Object item : list) {
                        element.addChild(convertElementToNode(childTag, item));
                    }
                }
                else {
                    element.addChild(convertElementToNode(childTag, childValue));
                }
            }
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

    public HtmlNode convertDocumentToNode(Map<String, Object> rawJson) {
        HtmlNode htmlElement = new HtmlNode("html");

        if (rawJson.containsKey("language")) {
            htmlElement.addAttribute("lang", String.valueOf(rawJson.get("language")));
        }

        if (rawJson.containsKey("head")) {
            htmlElement.addChild(convertElementToNode("head", rawJson.get("head")));
        }

        if (rawJson.containsKey("body")) {
            htmlElement.addChild(convertElementToNode("body", rawJson.get("body")));
        }

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

    private HtmlNode convertMetaToNode(Object value) {
        HtmlNode metaElement = new HtmlNode("meta");

        Map<String, String> attributes = convertAttributes(value);

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            metaElement.addAttribute(entry.getKey(), entry.getValue());
        }

        return metaElement;

    }

    private List<Object> convertGroupedMetaElements(Map<String, Object> rawMap) {
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

                String content = buildViewportContent(viewportMap);

                Map<String, String> attributes = new LinkedHashMap<>();

                attributes.put("name", "viewport");
                attributes.put("content", content);

                metaElements.add(createElementWithAttributes("meta", attributes));
            }
        }

        return metaElements;
    }

    private List<HtmlNode> expandMetaElementsToNode(Map<?, ?> rawMap) {
        List<HtmlNode> metaElements = new ArrayList<>();

        for (Map.Entry<?, ?> entry : rawMap.entrySet()) {

            String key = String.valueOf(entry.getKey());
            Object value = entry.getValue();

            if ("charset".equals(key)) {
                HtmlNode metaNode = new HtmlNode("meta");
                metaNode.addAttribute("charset", String.valueOf(value));
                metaElements.add(metaNode);
            }

            else if ("viewport".equals(key) && value instanceof Map<?, ?> viewportMap) {
                HtmlNode metaNode = new HtmlNode("meta");
                metaNode.addAttribute("name", "viewport");
                metaNode.addAttribute("content", buildViewportContent(viewportMap));
                metaElements.add(metaNode);
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

                if ("style".equals(key) && attributeValue instanceof Map<?, ?> styleMap) {
                    attributes.put(key, buildStyleAttributes(styleMap));
                }
                else {
                    attributes.put(key, String.valueOf(attributeValue));
                }
            }
        }
        return attributes;
    }

    private boolean canBeTreatedAsAttributes(Map<?, ?> rawMap) {

        for (Object value : rawMap.values()) {
            if (value instanceof Map || value instanceof List) {
                return false;
            }
        }
        return true;
    }

    private boolean isVoidElement(String tag) {
        return HtmlRules.VOID_ELEMENTS.contains(tag.toLowerCase());
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
            children.addAll(convertGroupedMetaElements(metaMap));
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

    private String buildViewportContent(Map<?, ?> viewportMap) {
        StringBuilder content = new StringBuilder();
        int index = 0;

        for (Map.Entry<?, ?> viewportEntry : viewportMap.entrySet()) {
            if (index > 0) {
                content.append(", ");
            }

            content.append(viewportEntry.getKey())
                    .append("=")
                    .append(String.valueOf(viewportEntry.getValue()));

            index++;
        }

        return content.toString();
    }

    private String buildStyleAttributes(Map<?, ?> styleMap) {
        StringBuilder css = new StringBuilder();

        for (Map.Entry<?, ?> styleEntry : styleMap.entrySet()) {
            css.append(styleEntry.getKey())
                    .append(": ")
                    .append(styleEntry.getValue())
                    .append("; ");
        }
        return css.toString().trim();
    }

    private Map<String, Object> createElement(String tag) {
        Map<String, Object> element = new LinkedHashMap<>();
        element.put("tag", tag);

        return element;
    }

}
