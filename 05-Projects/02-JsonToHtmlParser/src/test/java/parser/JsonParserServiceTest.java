package parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

public class JsonParserServiceTest {

    @Test
    void validJsonIsParsedSuccessfully() {

        JsonParserService parser = new JsonParserService();
        String json = "{\"name\":\"Marx\"}";
        Map<String, Object> result = parser.parseJson(json);

        assertEquals("Marx", result.get("name"));
    }

    @Test
    void bankJsonIsRejected () {
        JsonParserService parser = new JsonParserService();

        assertThrows(IllegalArgumentException.class, () -> parser.parseJson("  "));
    }

    @Test
    void invalidJsonIsRejected() {
        JsonParserService parser = new JsonParserService();
        String json = "{ invalid json }";

    assertThrows(IllegalArgumentException.class, () -> parser.parseJson(json));
    }
}
