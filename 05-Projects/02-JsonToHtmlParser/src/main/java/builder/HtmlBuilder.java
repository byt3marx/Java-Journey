package builder;

import model.HtmlNode;

import html.HtmlRules;
import java.util.Map;
import java.util.List;

public class HtmlBuilder {

    public String buildHtml(HtmlNode node, int depth) {

        String tag = node.getTag();
        String text = node.getText();
        Map<String, String> attributes = node.getAttributes();
        List<HtmlNode> children = node.getChildren();

        String attributesHtml = buildAttributes(attributes);

        boolean shouldRenderMultiline = node.hasChildren() && hasNodeBlockChildren(children);

        if (!shouldRenderMultiline) {
            return buildSingleLineNodeElement(
                    tag,
                    text,
                    attributesHtml,
                    children,
                    depth
            );
        }

        return buildMultiLineNodeElement(
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

    private boolean hasNodeBlockChildren(List<HtmlNode> children) {

        for (HtmlNode child : children) {
            String childTag = child.getTag();

            if (!isInlineTag(childTag)) {
                return true;
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

    private String buildSingleLineNodeElement(
            String tag,
            String text,
            String attributesHtml,
            List<HtmlNode> children,
            int depth) {

        StringBuilder html = new StringBuilder();

        appendOpeningTag(html, tag, attributesHtml, depth);

        if (isVoidElement(tag)) {
            return html.toString();
        }

        html.append(text);
        appendInlineNodeChildren(html, children);
        appendClosingTag(html, tag);

        return html.toString();

    }

    private String buildMultiLineNodeElement(
            String tag,
            String attributesHtml,
            List<HtmlNode> children,
            int depth
    ) {
        StringBuilder html = new StringBuilder();

        appendOpeningTag(html, tag, attributesHtml, depth);
        html.append("\n");
        appendMultiLineNodeChildren(html, children, depth);
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

    private void appendInlineNodeChildren(StringBuilder html, List<HtmlNode> children) {
        for (HtmlNode child : children) {
            html.append(buildHtml(child, 0));
        }
    }

    private void appendMultiLineNodeChildren(
            StringBuilder html,
            List<HtmlNode> children,
            int depth
    ) {

        for (HtmlNode child : children) {
            html.append(buildHtml(child, depth + 1))
                    .append("\n");
        }
    }
}
