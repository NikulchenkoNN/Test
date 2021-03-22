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

    public void threads() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter path to text file");
        String filePath = br.readLine();

        System.out.println("enter path to download");
        String destDirPath = br.readLine();

        System.out.println("enter rate limit to download");
        double rateLimit = Double.parseDouble(br.readLine());
        br.close();

        List<String> urlsList = reader.readFromFile(filePath);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (String str : urlsList) {
            executorService.submit(() -> {
                try {
                    downloader.download(str, destDirPath, rateLimit);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}