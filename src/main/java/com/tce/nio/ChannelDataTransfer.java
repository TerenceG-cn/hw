package com.tce.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 通道间的数据传输
 * transfer()和transTo()
 */
public class ChannelDataTransfer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("src\\main\\java\\com\\tce\\nio\\fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("src\\main\\java\\com\\tce\\nio\\toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        //public abstract long transferFrom(ReadableByteChannel src,long position, long count) throws IOException;
        toChannel.transferFrom(fromChannel,position, count);
        /**
         * 在SoketChannel的实现中，SocketChannel只会传输此刻准备好的数据（可能不足count字节）。
         * 因此，SocketChannel可能不会将请求的所有数据(count个字节)全部传输到FileChannel中。
         */
        //public abstract long transferTo(long position, long count,WritableByteChannel target)throws IOException;
        //fromChannel.transferTo(position, count, toChannel);//如果是SocketChannel会一直传输数据直到目标buffer被填满。
        fromFile.close();
        toFile.close();
    }
}
