package ru.test.ReaderFromFile;


import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Assert;
import org.junit.Test;
import ru.test.Downloader.DownloaderImpl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReaderFromFileFileImplTest {
    @Test
    public void reader() throws IOException {
        ReaderFromFileFileImpl reader = new ReaderFromFileFileImpl();

        File file = new File(getClass().getClassLoader().getResource("test.txt").getFile());
        List<String> strings = reader.readFromFile(String.valueOf(file));
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
        long start = System.nanoTime();
        downloader.download("https://download.jetbrains.com/go/goland-2020.3.4.exe", "d:/dir3", 500);
        long end = System.nanoTime();
        long time = (end-start)/1_000_000_000;
        System.out.printf("downloading time %d \n", time);
        File file = new File("d:/dir3/goland-2020.3.4.exe");
        System.out.println("average downloading speed is " + (file.length()/1024)/time + " kb/s");
    }
}