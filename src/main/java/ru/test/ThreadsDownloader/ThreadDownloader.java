package ru.test.ThreadsDownloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.Downloader.DownloaderImpl;
import ru.test.ReaderFromFile.ReaderFromFileFileImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadDownloader {

    @Autowired
    ReaderFromFileFileImpl reader;
    @Autowired
    DownloaderImpl downloader;

    String filePath = null;
    String destDirPath = null;
    double rateLimit = 0;
    BufferedReader br = null;

    public void threadDownloader() {

        br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("enter path to text file");
            filePath = br.readLine();
        } catch (IOException e) {
            System.out.println("not valid path to text file");
        }
        try {
            System.out.println("enter path to download");
            destDirPath = br.readLine();
        } catch (IOException e) {
            System.out.println("not valid path to download directory");
        }
        try {
            System.out.println("enter rate limit to download");
            rateLimit = Double.parseDouble(br.readLine());
        } catch (IOException e) {
            System.out.println("not valid rate limit");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        List<String> urlsList = reader.readFromFile(filePath);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (String str : urlsList) {
            executorService.submit(() -> {
                downloader.download(str, destDirPath, rateLimit);
            });
        }
        executorService.shutdown();
    }
}