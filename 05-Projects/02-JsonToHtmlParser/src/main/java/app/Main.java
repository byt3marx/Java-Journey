package app;

import builder.HtmlBuilder;
import parser.JsonParserService;
import parser.JsonToHtmlNodeConverter;

import java.util.Map;
import io.JsonLoader;

public class Main {

    public static void main(String[] args) {
/*
        HtmlBuilder builder = new HtmlBuilder();
//-------------------------------------------------------------------------------------Test 1
        Map<String, Object> p1 = new HashMap<>();
        p1.put("tag", "p");
        p1.put("text", "hello");

        Map<String, Object> p2 = new HashMap<>();
        p2.put("tag", "p");
        p2.put("text", "World");

        List<Object> children = new ArrayList<>();
        children.add(p1);
        children.add(p2);

        Map<String, Object> div = new HashMap<>();
        div.put("tag", "div");
        div.put("children", children);

        String result = builder.buildHtml(div, 0);
//--------------------------------------------------------------------------------------Test 2
        Map<String, Object> strong = new HashMap<>();
        strong.put("tag", "strong");
        strong.put("text", "World");

        List<Object> paragraphChildren = new ArrayList<>();
        paragraphChildren.add("Hello ");
        paragraphChildren.add(strong);

        Map<String, Object> paragraph = new HashMap<>();
        paragraph.put("tag", "p");
        paragraph.put("children", paragraphChildren);

        String result1 = builder.buildHtml(paragraph, 0);
//--------------------------------------------------------------------------------------Test 3
        Map<String, Object> p3 = new HashMap<>();
        p3.put("tag", "p");
        p3.put("text", "Hello");

        Map<String, Object> em = new HashMap<>();
        em.put("tag", "em");
        em.put("text", "example");

        List<Object> p4Children = new ArrayList<>();
        p4Children.add("This is an ");
        p4Children.add(em);
        p4Children.add(" text.");

        Map<String, Object> p4 = new HashMap<>();
        p4.put("tag", "p");
        p4.put("children", p4Children);

        List<Object> divChildren = new ArrayList<>();
        divChildren.add(p3);
        divChildren.add(p4);

        Map<String, Object> div1 = new HashMap<>();
        div1.put("tag", "div");
        div1.put("children", divChildren);

        String result2 = builder.buildHtml(div1, 0);
//--------------------------------------------------------------------------------------Test 4

        Map<String, String> attributes = new HashMap<>();
        attributes.put("href", "https://example.com");
        attributes.put("target", "_blank");

        Map<String, Object> link = new HashMap<>();
        link.put("tag", "a");
        link.put("text", "Visit site");
        link.put("attributes", attributes);

        String result3 = builder.buildHtml(link, 0);
//--------------------------------------------------------------------------------------Test 5

        Map<String, String> divAttributes = new HashMap<>();
        divAttributes.put("class", "container");

        Map<String, Object> em1 = new HashMap<>();
        em1.put("tag", "em");
        em1.put("text", "website");

        List<Object> paragraphChildren1 = new ArrayList<>();
        paragraphChildren1.add("Welcome to my ");
        paragraphChildren1.add(em1);

        Map<String, Object> paragraph1 = new HashMap<>();
        paragraph1.put("tag", "p");
        paragraph1.put("children", paragraphChildren1);

        Map<String, String> linkAttributes = new HashMap<>();
        linkAttributes.put("href", "https://example.com");

        Map<String, Object> link1 = new HashMap<>();
        link1.put("tag", "a");
        link1.put("text", "Visit site");
        link1.put("attributes", linkAttributes);

        List<Object> divChildren1 = new ArrayList<>();
        divChildren1.add(paragraph1);
        divChildren1.add(link1);

        Map<String, Object> div2 = new HashMap<>();
        div2.put("tag", "div");
        div2.put("attributes", divAttributes);
        div2.put("children", divChildren1);

        String result4 = builder.buildHtml(div2, 0);
//--------------------------------------------------------------------------------------Test 6

        Map<String, Object> rawDiv = new LinkedHashMap<>();
        rawDiv.put("h1", "Title");
        rawDiv.put("p", "Paragraph");

        JsonToHtmlNodeConverter converter = new JsonToHtmlNodeConverter();
        Map<String, Object> converted = converter.convertElement("div", rawDiv);

        String result5 = builder.buildHtml(converted, 0);
//--------------------------------------------------------------------------------------Test X

        String json = """
                {
                "div": {
                "h1": "Title",
                "p": "Paragraph"
                 }
                }
                """;

        JsonParserService parser = new JsonParserService();
        Map<String, Object> rawJson = parser.parseJson(json);

        JsonToHtmlNodeConverter converter1 = new JsonToHtmlNodeConverter();

        Object divValue = rawJson.get("div");
        Map<String, Object> converted1 = converter.convertElement("div", divValue);

        String result6 = builder.buildHtml(converted1, 0);

        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
        */

        JsonLoader loader = new JsonLoader();
        JsonParserService parser = new JsonParserService();
        JsonToHtmlNodeConverter converter = new JsonToHtmlNodeConverter();
        HtmlBuilder builder = new HtmlBuilder();

        String json = loader.readFile("src/main/resources/input/pageNotFound.json");

        Map<String, Object> rawJson = parser.parseJson(json);
/*
        Object bodyValue = rawJson.get("body");
        Map<String, Object> converted = converter.convertElement("body", bodyValue);

 */
        Map<String, Object> converted = converter.convertDocument(rawJson);

        String result = builder.buildHtml(converted, 0);

        String doctype = "<!DOCTYPE html>\n";

        System.out.println(doctype + result);
    }
}
