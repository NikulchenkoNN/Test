import java.io.IOException;



public interface Downloader {
    void download(String fileAddress, String filePath) throws IOException;
}
