package ru.test.Downloader;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.io.InputStream;

public final class ThrottledInputStream extends InputStream {
    private final InputStream is;
    private final RateLimiter rateLimiter;

    public ThrottledInputStream(InputStream is, double rateLimit) {
        this.is = is;
        this.rateLimiter = RateLimiter.create(rateLimit);
    }

    @Override
    public int read(byte[] b) throws IOException {
        rateLimiter.acquire();
        return is.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        rateLimiter.acquire();
        return is.read(b, off, len);
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        rateLimiter.acquire();
        return is.readAllBytes();
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        rateLimiter.acquire();
        return is.readNBytes(len);
    }

    @Override
    public int readNBytes(byte[] b, int off, int len) throws IOException {
        rateLimiter.acquire();
        return is.readNBytes(b, off, len);
    }

    @Override
    public int read() throws IOException {
        rateLimiter.acquire();
        return is.read();
    }

    @Override
    public void close() throws IOException {
        is.close();
    }
}
