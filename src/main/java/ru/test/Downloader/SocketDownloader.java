package ru.test.Downloader;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;


@Component
public class SocketDownloader implements Downloader{

    @Override
    public void download(String fileAddress, String filePath, double rateLimit) throws IOException {
        Socket socket = new Socket();
        URL url = new URL(fileAddress);
        SocketAddress socketAddress = new InetSocketAddress(fileAddress, url.getPort());
        SocketChannel socketChannel = SocketChannel.open();
        socket.connect(socketAddress);
        socket.setPerformancePreferences(500, 500, 200);
        File file = new File(filePath, fileAddress.substring(fileAddress.lastIndexOf("/")));
        FileChannel fileChannel = new RandomAccessFile(file, "r").getChannel();
        fileChannel.transferTo(0, fileChannel.size(), socketChannel);
    }
}
