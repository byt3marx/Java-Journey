package app;

import model.HtmlNode;

import builder.HtmlBuilder;
import parser.JsonParserService;
import parser.JsonToHtmlNodeConverter;

import java.util.Map;
import io.JsonLoader;
import io.HtmlFileWriter;

public class Main {

    public static void main(String[] args) {

        String inputPath = "src/main/resources/input/helloWorld.json";
        String outputPath = "src/main/resources/output/helloWorld.html";

        JsonLoader loader = new JsonLoader();
        JsonParserService parser = new JsonParserService();
        JsonToHtmlNodeConverter converter = new JsonToHtmlNodeConverter();
        HtmlBuilder builder = new HtmlBuilder();
        HtmlFileWriter writer = new HtmlFileWriter();

        String json = loader.readFile(inputPath);

        Map<String, Object> rawJson = parser.parseJson(json);

        //Map<String, Object> converted = converter.convertDocument(rawJson);

        HtmlNode converted = converter.convertDocumentToNode(rawJson);

        String result = builder.buildHtml(converted, 0);

        String doctype = "<!DOCTYPE html>\n";

        String finalHtml = doctype + result;

        writer.writeFile(outputPath, finalHtml);

        System.out.println("HTML file generated successfully.");
    }
}
