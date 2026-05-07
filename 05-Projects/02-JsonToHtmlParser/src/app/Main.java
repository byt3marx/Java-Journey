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

        Map<String, Object> p3 = new HashMap<>();
        p3.put("tag", "p");
        p3.put("text", "Hello");

        Map<String, Object> em = new HashMap<>();
        em.put("tag", "em");
        em.put("text", "example");

        List<Object> p4Children = new ArrayList<>();
        p4Children.add("This is an ");
        p4Children.add(em);
        p4Children.add("text.");

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

        System.out.println(result);

        System.out.println(result1);

        System.out.println(result2);
    }
}
