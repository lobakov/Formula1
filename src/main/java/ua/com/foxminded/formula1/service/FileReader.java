package ua.com.foxminded.formula1.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Reader {

    public List<String> read(String fileName) {
        Path file = Paths.get(fileName);
        List<String> content = new ArrayList<>();

        try {
            content = Files.readAllLines(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return content;
    }
}
