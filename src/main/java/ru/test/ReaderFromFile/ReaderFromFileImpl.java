package ru.test.ReaderFromFile;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReaderFromFileImpl implements ReaderFromFile {


    @Override
    public List<String> readFromFile(String fileName) {
        ArrayList<String> urls = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.ready()) {
                urls.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
}