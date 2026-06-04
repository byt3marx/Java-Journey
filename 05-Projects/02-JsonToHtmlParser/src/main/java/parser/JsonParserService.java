package parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonParserService {

    private final Gson gson = new Gson();

    public Map<String, Object> parseJson(String json) {

        if (json == null || json.isBlank()) {
            throw new IllegalArgumentException("JSON content cannot be empty");
        }

        try {
            Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
            return gson.fromJson(json, mapType);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse JSON", e);
        }
    }
}
