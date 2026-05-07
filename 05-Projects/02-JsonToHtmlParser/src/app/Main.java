package app;

import builder.HtmlBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

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

        HtmlBuilder builder = new HtmlBuilder();

        String result = builder.buildHtml(div, 0);

        System.out.println(result);
    }
}
