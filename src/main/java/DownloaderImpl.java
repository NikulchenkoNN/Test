
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Component
public class DownloaderImpl implements Downloader {
    @Override
    public void download(String fileAddress, String filePath) throws IOException {
        URL website = new URL(fileAddress);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.getChannel().transferFrom(rbc,0,Long.MAX_VALUE);
    }
}
