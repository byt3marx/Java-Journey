package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

public class JsonParserServiceTest {

    private JsonParserService parser;

    @BeforeEach
    void setUp() {
        parser = new JsonParserService();
    }

    @Test
    void validJsonIsParsedSuccessfully() {

        // Arrange
        String json = "{\"name\":\"Marx\"}";

        // Act
        Map<String, Object> result = parser.parseJson(json);

        // Assert
        assertEquals("Marx", result.get("name"));
    }

    @Test
    void blankJsonIsRejected () {

        // Arrange
        String json = "  ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> parser.parseJson("  "));
    }

    @Test
    void invalidJsonIsRejected() {

        // Arrange
        String json = "{ invalid json }";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> parser.parseJson(json));
    }
}
