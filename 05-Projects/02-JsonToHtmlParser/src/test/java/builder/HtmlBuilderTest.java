package builder;

import model.HtmlNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HtmlBuilderTest {

    private HtmlBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new HtmlBuilder();
    }

    @Test
    void simpleTextNodeRendersCorrectHtml() {

        // Arrange
        HtmlNode h1 = new HtmlNode("h1");
        h1.setText("Hello");

        // Act
        String result = builder.buildHtml(h1, 0);

        // Assert
        assertEquals("<h1>Hello</h1>", result);
    }

    @Test
    void elementWithAttributeAndTextRendersCorrectHtml() {

        // Arrange
        HtmlNode a = new HtmlNode("a");

        a.addAttribute("href", "http://Example.com");
        a.setText("Visit site");

        // Act
        String result = builder.buildHtml(a, 0);

        // Assert
        assertEquals("<a href=\"http://Example.com\">Visit site</a>", result);
    }

    @Test
    void elementWithAttributesRendersCorrectHtml() {

        // Arrange
        HtmlNode div = new HtmlNode("div");
        div.addAttribute("class", "container");

        // Act
        String result = builder.buildHtml(div, 0);

        // Assert
        assertEquals("<div class=\"container\"></div>", result);
    }

    @Test
    void parentWithChildRendersMultilineHtml() {

        // Arrange
        HtmlNode div = new HtmlNode("div");
        HtmlNode p = new HtmlNode("p");
        p.setText("Hello");
        div.addChild(p);

        // Act
        String result = builder.buildHtml(div, 0);

        // Assert
        assertEquals("<div>\n  <p>Hello</p>\n</div>", result);
    }

    @Test
    void parentWithAttributeAndChildRendersCorrectHtml() {

        // Arrange
        HtmlNode div = new HtmlNode("div");
        div.addAttribute("id", "main");
        HtmlNode p = new HtmlNode("p");
        p.setText("Hello");
        div.addChild(p);

        // Act
        String result = builder.buildHtml(div, 0);

        // Assert
        assertEquals("<div id=\"main\">\n  <p>Hello</p>\n</div>", result);
    }

    @Test
    void voidElementRendersWithoutClosingTag() {

        // Arrange
        HtmlNode meta = new HtmlNode("meta");
        meta.addAttribute("charset", "utf-8");

        // Act
        String result = builder.buildHtml(meta, 0);

        // Assert
        assertEquals("<meta charset=\"utf-8\">", result);
    }

    @Test
    void inlineChildRendersOnSameLine() {

        // Arrange
        HtmlNode p = new HtmlNode("p");
        p.setText("Hello ");

        HtmlNode strong = new HtmlNode("strong");
        strong.setText("world");

        p.addChild(strong);

        // Act
        String result = builder.buildHtml(p, 0);
        System.out.println(result);

        // Assert
        assertEquals("<p>Hello <strong>world</strong></p>", result);
    }

    @Test
    void elementWithMultipleChildrenRendersCorrectHtml() {

        // Arrange
        HtmlNode div = new HtmlNode("div");
        HtmlNode h1 = new HtmlNode("h1");
        HtmlNode p = new HtmlNode("p");

        h1.setText("Title");
        p.setText("Hello");

        div.addChild(h1);
        div.addChild(p);

        // Act
        String result = builder.buildHtml(div, 0);

        // Assert
        assertEquals("<div>\n  <h1>Title</h1>\n  <p>Hello</p>\n</div>" , result);
    }

    @Test
    void deeplyNestedElementsRenderWithCorrectIndentation() {

        // Arrange
        HtmlNode div = new HtmlNode("div");
        HtmlNode section = new HtmlNode("section");
        HtmlNode p = new HtmlNode("p");

        p.setText("Hello");

        div.addChild(section);
        section.addChild(p);

        // Act
        String result = builder.buildHtml(div, 0);

        // Assert
        assertEquals("<div>\n  <section>\n    <p>Hello</p>\n  </section>\n</div>", result);
    }
}
