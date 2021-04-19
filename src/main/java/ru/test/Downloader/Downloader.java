package ru.test.Downloader;

import java.io.IOException;

public interface Downloader {
    void download(String fileAddress, String filePath, double rateLimit) throws IOException;
}