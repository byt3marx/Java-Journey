package parser;

import com.google.gson.Gson;
import java.util.Map;

public class JsonParserService {

    private final Gson gson = new Gson();

    public Map<String, Object> parseJson(String json) {
        return gson.fromJson(json, Map.class);
    }
}
