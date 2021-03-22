package ru.test.Downloader;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class DownloaderImpl implements Downloader {
    /**
     * @param url      url to download from
     * @param filePath path to download
     * @throws IOException
     */
    @Override
    public void download(String url, String filePath, double rateLimit) throws IOException {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ThrottledOutputStream tos = null;
        if (!Files.isDirectory(Path.of(filePath))) {
            Files.createDirectory(Path.of(filePath));
        }
        File file = new File(filePath, url.substring(url.lastIndexOf("/")));
        if (!file.exists()) {
            try {
                bis = new BufferedInputStream(new URL(url).openStream());
                fos = new FileOutputStream(file);
                tos = new ThrottledOutputStream(fos, rateLimit);

                final byte[] bytes = new byte[1024];
                int count;
                while ((count = bis.read(bytes)) != -1) {
                    tos.write(bytes, 0, count);
                }
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (tos != null) {
                    tos.close();
                }
            }
        }
    }
}
