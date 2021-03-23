package ru.test.ReaderFromFile;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReaderFromFileFileImpl implements ReaderFromFile {

    BufferedReader br = null;

    @Override
    public List<String> readFromFile(String fileName) {
        ArrayList<String> urls = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            while (br.ready()) {
                urls.add(br.readLine());
            }
        } catch (IOException e) {
            System.out.println("can not read from file");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }
}