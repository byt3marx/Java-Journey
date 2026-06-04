package parser;

import model.HtmlNode;

import html.HtmlRules;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonToHtmlNodeConverter {

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
                    element.addAttribute(
                            attribute.getKey(),
                            attribute.getValue());
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

    private HtmlNode convertMetaToNode(Object value) {
        HtmlNode metaElement = new HtmlNode("meta");

        Map<String, String> attributes = convertAttributes(value);

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            metaElement.addAttribute(entry.getKey(), entry.getValue());
        }

        return metaElement;

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

}
