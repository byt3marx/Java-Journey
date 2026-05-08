package parser;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class JsonToHtmlNodeConverter {

    public Map<String, Object> convertElement(String tag, Object value) {
        Map<String, Object> element = new LinkedHashMap<>();

        element.put("tag", tag);

        if (value instanceof String) {
            element.put("text", value);
            return element;
        }

        if (value instanceof Map) {
            Map<String, Object> rawMap = (Map<String, Object>) value;
            List<Object> children = new ArrayList<>();

            for (Map.Entry<String, Object> entry : rawMap.entrySet()) {
                String childTag = entry.getKey();
                Object childValue = entry.getValue();

                if (childTag.equals("attributes")) {
                    element.put("attributes", childValue);
                } else {
                    children.add(convertElement(childTag, childValue));
                }
            }

            element.put("children", children);
            return element;
        }

        return element;

    }

}
