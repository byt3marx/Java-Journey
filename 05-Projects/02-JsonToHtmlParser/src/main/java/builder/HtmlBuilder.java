package builder;

import java.util.Map;
import java.util.List;
import java.util.Set;

public class HtmlBuilder {

    private static final Set<String> INLINE_TAGS = Set.of("strong", "em", "span", "a");

    private static final Set<String> VOID_ELEMENTS = Set.of("meta", "link", "img", "br", "hr", "input");

    public String buildHtml(Object node, int depth) {

        if (node instanceof String) {
            return (String) node;
        }

        if (!(node instanceof Map)) {
            return "";
        }

        Map<String, Object> map = (Map<String, Object>) node;

        String tag = (String) map.get("tag");
        if (tag == null || tag.isBlank()) {
            throw new IllegalArgumentException("Missing tag");
        }

        String text = (String) map.get("text");
        if (text == null) {
            text = "";
        }

        Map<String, String> attributes = (Map<String, String>) map.get("attributes");
        String attributesHtml = buildAttributes(attributes);

        List<Object> children = (List<Object>) map.get("children");

        StringBuilder html = new StringBuilder();

        boolean shouldRenderMultiline = children != null && hasBlockChildren(children);

        if (children == null || !shouldRenderMultiline) {
            html.append(indent(depth))
                    .append("<")
                    .append(tag)
                    .append(attributesHtml)
                    .append(">");

            if (isVoidElement(tag)) {
                return html.toString();
            }

            html.append(text);

            if (children != null) {
                for (Object child : children) {
                    html.append(buildHtml(child, 0));
                }
            }

            html.append("</").append(tag).append(">");

            return html.toString();
        }

        html.append(indent(depth))
                .append("<")
                .append(tag)
                .append(attributesHtml)
                .append(">")
                .append("\n");

        for (Object child : children) {
            html.append(buildHtml(child, depth + 1))
                    .append("\n");
        }

        html.append(indent(depth))
                .append("</").append(tag).append(">");

        return html.toString();
    }

    private String indent(int depth) {
        return "  ".repeat(depth);
    }

    private boolean isInlineTag(String tag) {
        return INLINE_TAGS.contains(tag.toLowerCase());
    }

    private boolean hasBlockChildren(List<Object> children) {

        for (Object child : children) {

            if (child instanceof Map) {
                Map<String, Object> childMap = (Map<String, Object>) child;
                String childTag = (String) childMap.get("tag");

                if (childTag != null && !isInlineTag(childTag)) {
                    return true;
                }
            }
        }

        return false;
    }

    private String buildAttributes(Map<String, String> attributes) {

        if (attributes == null || attributes.isEmpty()) {
            return "";
        }

        StringBuilder attributesHtml = new StringBuilder();

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            attributesHtml.append(" ")
                    .append(entry.getKey())
                    .append("=\"")
                    .append(entry.getValue())
                    .append("\"");
        }
        return attributesHtml.toString();

    }

    private boolean isVoidElement(String tag) {
        return VOID_ELEMENTS.contains(tag.toLowerCase());
    }

}
