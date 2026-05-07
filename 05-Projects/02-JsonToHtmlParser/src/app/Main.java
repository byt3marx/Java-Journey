package app;

import builder.HtmlBuilder;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Object> test = new HashMap<>();
        test.put("tag", "p");
        test.put("text", "hello");

        HtmlBuilder builder = new HtmlBuilder();
        String result = builder.buildHtml(test, 0);

        System.out.println(result);
    }
}
