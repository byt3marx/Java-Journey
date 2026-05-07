package builder;

import java.util.Map;

public class HtmlBuilder {

    public String buildHtml(Object node, int depth) {
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

        return "<" + tag + ">" + text + "</" + tag + ">";
    }
}
