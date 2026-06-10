package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HtmlNodeTest {

    @Test
    void createsNodeWithEmptyInitialState() {
        HtmlNode node = new HtmlNode("div");

        assertEquals("div", node.getTag());
        assertFalse(node.hasChildren());
        assertFalse(node.hasAttributes());
        assertFalse(node.hasText());
    }

    @Test
    void addChildAddsChildAndUpdatesState() {
        HtmlNode node = new HtmlNode("div");
        HtmlNode childNode = new HtmlNode("child");
        node.addChild(childNode);

        assertTrue(node.hasChildren());
        assertEquals(1, node.getChildren().size());
        assertEquals(childNode, node.getChildren().get(0));
    }

    @Test
    void constructorRejectsBlankTag() {

        assertThrows(IllegalArgumentException.class, () -> new HtmlNode(""));
    }

    @Test
    void addAttributeAddsAttributeAndUpdatesState() {
        HtmlNode node = new HtmlNode("div");
        node.addAttribute("class", "container");

        assertTrue(node.hasAttributes());
        assertEquals(1, node.getAttributes().size());
        assertEquals("container", node.getAttributes().get("class"));
    }

    @Test
    void addChildRejectsNullChild() {
        HtmlNode node = new HtmlNode("div");

        assertThrows(IllegalArgumentException.class, () -> node.addChild(null));
    }

    @Test
    void setTextUpdatesTextState() {
        HtmlNode node = new HtmlNode("p");

        node.setText("Hello");

        assertTrue(node.hasText());
        assertEquals("Hello", node.getText());
    }
}
