import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadDownloader {
    ReaderFromFileFileImpl reader;
    DownloaderImpl downloader;

    public void threads() throws IOException {
        System.out.println("enter path to text file");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String filePath = br.readLine();
        System.out.println("enter rate limit to download");
        double rateLimit = Double.parseDouble(br.readLine());
        List<String> urlsList = reader.readFromFile(br.readLine());
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        RateLimiter rateLimiter = RateLimiter.create(rateLimit);
        for (String str : urlsList) {
            executorService.submit(() -> {
                rateLimiter.acquire();
                try {
                    downloader.download(str, filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}