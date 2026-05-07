package builder;

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

        List<Object> children = (List<Object>) map.get("children");

        StringBuilder html = new StringBuilder();

        if (children == null) {
            html.append(indent(depth))
                    .append("<").append(tag).append(">")
                    .append(text)
                    .append("</").append(tag).append(">");

            return html.toString();
        }

        html.append(indent(depth))
                .append("<").append(tag).append(">")
                .append("\n");

        for (Object child : children) {
            html.append(buildHtml(child, depth + 1))
                    .append("\n");
        }

        html.append(indent(depth))
                .append("</").append(tag).append(">");

        return html.toString();

        /*
        html.append("<").append(tag).append(">");

        html.append(text);

        if (children != null) {
            for (Object child : children) {
                html.append(buildHtml(child, depth + 1));
            }
        }

        html.append("</").append(tag).append(">");

        return html.toString();
        */
    }

    private String indent(int depth) {
        return "  ".repeat(depth);
    }
}
