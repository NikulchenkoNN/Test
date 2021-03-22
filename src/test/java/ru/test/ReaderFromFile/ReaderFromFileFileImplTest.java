package ru.test.ReaderFromFile;


import org.junit.Test;
import ru.test.Downloader.DownloaderImpl;
import ru.test.Downloader.SocketDownloader;

import java.io.IOException;
import java.util.List;

public class ReaderFromFileFileImplTest {
    @Test
    public void reader() throws IOException {
        ReaderFromFileFileImpl reader = new ReaderFromFileFileImpl();

        List<String> list = reader.readFromFile("d:/test.txt");
        for (String s :
                list) {
            System.out.println(s);
        }
    }

    @Test
    public void lightDownloader() throws IOException {
        DownloaderImpl downloader = new DownloaderImpl();


        long start = System.nanoTime();
        downloader.download("https://vargr.ru/dmod-white/D%20Mod%20White%20v.7.7.exe", "d:/dir", 100);
        long end = System.nanoTime();
        System.out.println((end - start)/1_000_000_000);
    }

    @Test
    public void socketDownloader() throws IOException {
        SocketDownloader downloader = new SocketDownloader();

        long start = System.nanoTime();
        downloader.download("https://vargr.ru/dmod-white/D%20Mod%20White%20v.7.7.exe", "d:/dir", 100);
        long end = System.nanoTime();
        System.out.println((end - start)/1_000_000_000);
    }
}