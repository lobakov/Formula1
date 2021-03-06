package ua.com.foxminded.formula1.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ua.com.foxminded.formula1.exception.InvalidInputStreamException;

public class FileReader implements Reader {

    @Override
    public List<String> read(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File " + fileName + " was not found. Check the file and try again");
        }

        List<String> content = new ArrayList<>();
        try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
        } catch (IOException e) {
            throw new InvalidInputStreamException("Error reading from file " + fileName
                    + " Check file type and try again");
        }
        return content;
    }
}
