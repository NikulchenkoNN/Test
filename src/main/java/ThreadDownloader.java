import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadDownloader {
    DownloaderImpl downloader;
    public void threads(List<String> urlsList, String filePath, double rateLimit) {
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
