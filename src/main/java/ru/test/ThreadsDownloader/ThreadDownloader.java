package ru.test.ThreadsDownloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.test.Downloader.Downloader;
import ru.test.Downloader.DownloaderImpl;
import ru.test.ReaderFromFile.ReaderFromFile;
import ru.test.ReaderFromFile.ReaderFromFileFileImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadDownloader {
    ReaderFromFile reader;
    Downloader downloader;

    String filePath = null;

    @Value("${default.destDirPath}")
    String destDirPath;
    @Value("${default.rateLimit}")
    double rateLimit;

    public void threadDownloader() throws IOException {


        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("enter path to text file");
            filePath = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = new ReaderFromFileFileImpl();
        downloader = new DownloaderImpl();


        try {
            Files.createDirectory(Path.of(destDirPath));
        } catch (IOException e) {
            System.out.println("can not create directory or directory already exist");
        }


        List<String> urlsList = reader.readFromFile(filePath);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
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