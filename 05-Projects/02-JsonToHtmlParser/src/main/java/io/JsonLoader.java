package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonLoader {

    public String readFile(String filePath) {

        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path cannot be empty");
        }

        try {
            Path path = Path.of(filePath);
            return Files.readString(path);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
