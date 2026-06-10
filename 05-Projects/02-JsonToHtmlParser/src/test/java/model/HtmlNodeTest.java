package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HtmlNodeTest {

    @Test
    void createsNodeWithEmptyInitialState() {

        // Arrange
        HtmlNode node = new HtmlNode("div");

        // Assert
        assertEquals("div", node.getTag());
        assertFalse(node.hasChildren());
        assertFalse(node.hasAttributes());
        assertFalse(node.hasText());
    }

    @Test
    void addChildAddsChildAndUpdatesState() {

        // Arrange
        HtmlNode node = new HtmlNode("div");
        HtmlNode childNode = new HtmlNode("child");

        // Act
        node.addChild(childNode);

        // Assert
        assertTrue(node.hasChildren());
        assertEquals(1, node.getChildren().size());
        assertEquals(childNode, node.getChildren().get(0));
    }

    @Test
    void constructorRejectsBlankTag() {

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new HtmlNode(""));
    }

    @Test
    void addAttributeAddsAttributeAndUpdatesState() {

        // Arrange
        HtmlNode node = new HtmlNode("div");

        // Act
        node.addAttribute("class", "container");

        // Assert
        assertTrue(node.hasAttributes());
        assertEquals(1, node.getAttributes().size());
        assertEquals("container", node.getAttributes().get("class"));
    }

    @Test
    void addChildRejectsNullChild() {

        // Arrange
        HtmlNode node = new HtmlNode("div");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> node.addChild(null));
    }

    @Test
    void setTextUpdatesTextState() {

        // Arrange
        HtmlNode node = new HtmlNode("p");

        // Act
        node.setText("Hello");

        // Assert
        assertTrue(node.hasText());
        assertEquals("Hello", node.getText());
    }
}
