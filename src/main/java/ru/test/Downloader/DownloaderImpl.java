package ru.test.Downloader;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

@Component
public class DownloaderImpl implements Downloader {

    @Override
    public void download(String url, String filePath, double rateLimit) {

        File file = new File(filePath, url.substring(url.lastIndexOf("/")));
        if (!file.exists()) {
            try (
                    BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream());
                    FileOutputStream fos = new FileOutputStream(file);
                    ThrottledOutputStream tos = new ThrottledOutputStream(fos, rateLimit)
            ) {
                final byte[] bytes = new byte[1024];
                while (bis.read(bytes) != -1) {
                    tos.write(bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.printf("file %s already exist \n", url.substring(url.lastIndexOf("/")));
        }
    }
}