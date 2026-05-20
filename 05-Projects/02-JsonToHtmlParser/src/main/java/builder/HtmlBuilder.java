package builder;

import html.HtmlRules;
import java.util.Map;
import java.util.List;

public class HtmlBuilder {

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

        boolean shouldRenderMultiline = children != null && hasBlockChildren(children);

        if (children == null || !shouldRenderMultiline) {
            return buildSingleLineElement(
                    tag,
                    text,
                    attributesHtml,
                    children,
                    depth
            );
        }

        return buildMultiLineElement(
                tag,
                attributesHtml,
                children,
                depth
        );
    }

    private String indent(int depth) {
        return "  ".repeat(depth);
    }

    private boolean isInlineTag(String tag) {
        return HtmlRules.INLINE_TAGS.contains(tag.toLowerCase());
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
        return HtmlRules.VOID_ELEMENTS.contains(tag.toLowerCase());
    }

    private String buildSingleLineElement(
            String tag,
            String text,
            String attributesHtml,
            List<Object> children,
            int depth
    ) {
        StringBuilder html = new StringBuilder();

        appendOpeningTag(html, tag, attributesHtml, depth);

        if (isVoidElement(tag)) {
            return html.toString();
        }

        html.append(text);

        appendInlineChildren(html, children);

        appendClosingTag(html, tag);

        return html.toString();
    }

    private String buildMultiLineElement(
            String tag,
            String attributesHtml,
            List<Object> children,
            int depth
    ) {

        StringBuilder html = new StringBuilder();

        appendOpeningTag(html, tag, attributesHtml, depth);
        html.append("\n");

        appendMultiLineChildren(html, children, depth);

        html.append(indent(depth));

        appendClosingTag(html, tag);

        return html.toString();
    }

    private void appendOpeningTag(
            StringBuilder html,
            String tag,
            String attributesHtml,
            int depth
    ) {
        html.append(indent(depth))
                .append("<")
                .append(tag)
                .append(attributesHtml)
                .append(">");
    }

    private void appendClosingTag(StringBuilder html, String tag) {
        html.append("</").append(tag).append(">");
    }

    private void appendInlineChildren(StringBuilder html, List<Object> children) {
        if (children != null) {
            for (Object child : children) {
                html.append(buildHtml(child, 0));
            }
        }
    }

    private void appendMultiLineChildren(
            StringBuilder html,
            List<Object> children,
            int depth
    ) {
        for (Object child : children) {
            html.append(buildHtml(child, depth + 1))
                    .append("\n");
        }
    }

}
