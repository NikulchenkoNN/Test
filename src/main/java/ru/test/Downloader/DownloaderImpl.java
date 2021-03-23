package ru.test.Downloader;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class DownloaderImpl implements Downloader {

    @Override
    public void download(String url, String filePath, double rateLimit) {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ThrottledOutputStream tos = null;
        if (!Files.isDirectory(Path.of(filePath))) {
            try {
                Files.createDirectory(Path.of(filePath));
            } catch (IOException e) {
                System.out.println("can not create directory or directory already exist");
            }
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
            } catch (FileNotFoundException e) {
                System.out.println("wrong file address");
            } catch (MalformedURLException e) {
                System.out.println("not valid url");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                    if (tos != null) {
                        tos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else {
            System.out.printf("file with name %s already exist", url.substring(url.lastIndexOf("/")));
        }
    }
}
