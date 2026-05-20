package parser;

import com.google.gson.Gson;
import java.util.Map;

public class JsonParserService {

    private final Gson gson = new Gson();

    public Map<String, Object> parseJson(String json) {

        if (json == null || json.isBlank()) {
            throw new IllegalArgumentException("JSON content cannot be empty");
        }

        try {
            return gson.fromJson(json, Map.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse JSON", e);
        }
    }
}
