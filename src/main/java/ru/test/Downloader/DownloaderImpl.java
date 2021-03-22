package ru.test.Downloader;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Component
public class DownloaderImpl implements Downloader {
    /**
     * @param url      url to download from
     * @param filePath path to download
     * @throws IOException
     */
    @Override
    public void download(String url, String filePath, double rateLimit) throws IOException {
        RateLimiter limiter = RateLimiter.create(rateLimit);
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());

        File file = new File(filePath, url.substring(url.lastIndexOf("/")));

        FileOutputStream fos = new FileOutputStream(file);

        limiter.acquire();
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}
