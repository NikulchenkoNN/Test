package ru.test.ReaderFromFile;

import java.io.IOException;
import java.util.List;

public interface ReaderFromFile {
    List<String> readFromFile(String fileName) throws IOException;
}
