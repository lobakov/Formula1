package ua.com.foxminded.formula1.service;

import java.io.IOException;
import java.util.List;

public interface Reader {

    List<String> read(String url) throws IOException;
}
