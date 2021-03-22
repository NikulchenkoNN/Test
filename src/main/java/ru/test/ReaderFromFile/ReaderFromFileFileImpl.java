package ru.test.ReaderFromFile;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReaderFromFileFileImpl implements ReaderFromFile {

    @Override
    public List<String> readFromFile(String fileName) throws IOException {
        ArrayList<String> urls = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while (br.ready()) {
            urls.add(br.readLine());
        }
        br.close();
        return urls;
    }
}