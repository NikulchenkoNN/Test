package ru.test.ReaderFromFile;


import org.junit.Assert;
import org.junit.Test;
import ru.test.Downloader.DownloaderImpl;

import java.util.ArrayList;
import java.util.List;

public class ReaderFromFileFileImplTest {
    @Test
    public void reader() {
        ReaderFromFileFileImpl reader = new ReaderFromFileFileImpl();
        List<String> strings = reader.readFromFile("/test.txt");
        ArrayList<String> actual = new ArrayList<>();
        actual.add("https://vargr.ru/dmod-white/D%20Mod%20White%20v.7.7.exe");
        actual.add("https://download.jetbrains.com/webide/PhpStorm-2020.3.3.exe");
        actual.add("https://download.jetbrains.com/idea/ideaIU-2020.3.3.exe");
        actual.add("https://download.jetbrains.com/go/goland-2020.3.4.exe");
        Assert.assertEquals(strings, actual);
    }

    @Test
    public void downloader() {
        DownloaderImpl downloader = new DownloaderImpl();
        downloader.download("https://download.jetbrains.com/idea/ideaIU-2020.3.3.exe", "d:/dir3", 500);
    }
}