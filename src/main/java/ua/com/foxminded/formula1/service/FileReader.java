package ua.com.foxminded.formula1.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Reader {

    public List<String> read(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> content = new ArrayList<>();

        try {
            File file = new File(classLoader.getResource(fileName).getFile());
            content = Files.readAllLines(file.toPath());
        } catch (IOException exc) {
            throw new FileNotFoundException("File " + fileName + " was not found. Check the file and try again");
        }
        return content;
    }
}
