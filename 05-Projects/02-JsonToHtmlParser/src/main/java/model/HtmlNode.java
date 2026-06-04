package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Collections;

public class HtmlNode {

    private final String tag;
    private String text;
    private final Map<String, String> attributes;
    private final List<HtmlNode> children;

    public HtmlNode(String tag) {
        if (tag == null || tag.isBlank()) {
            throw new IllegalArgumentException("Tag cannot be empty");
        }

        this.tag = tag;
        this.text = "";
        this.attributes = new LinkedHashMap<>();
        this.children = new ArrayList<>();
    }

    public String getTag() {
        return tag;
    }

    public String getText() {
        return text;
    }

    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public List<HtmlNode> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        this.text = text;
    }

    public void addAttribute(String key, String value) {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or blank");
        }

        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        this.attributes.put(key, value);
    }

    public void addChild(HtmlNode child) {
        if (child == null) {
            throw new IllegalArgumentException("Child cannot be null");
        }

        this.children.add(child);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public boolean hasText() {
        return !text.isBlank();
    }

    public boolean hasAttributes() {
        return !attributes.isEmpty();
    }

}
