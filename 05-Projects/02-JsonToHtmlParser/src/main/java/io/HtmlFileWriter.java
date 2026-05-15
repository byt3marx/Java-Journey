package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HtmlFileWriter {

    public void writeFile(String filePath, String content) {
        try {
            Path path = Path.of(filePath);
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write HTML file", e);
        }
    }
}
