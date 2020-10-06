package com.tce.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\10352\\IdeaProjects\\hw\\src\\main\\webapp\\jsp\\data.jsp", "rw");
        FileChannel inChannel = aFile.getChannel();
        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf); //read into buffer.
        buf.flip();  //make buffer ready for read
        byte[] array= new byte[48];
        int i=0;
        while (buf.hasRemaining()) {
            //System.out.print((char) buf.get());
            array[i++]= buf.get(); // read 1 byte at a time
        }
        buf.clear();

        buf.put(array);
        buf.flip();
        while (buf.hasRemaining()) {
            System.out.print((char) buf.get());
        }

        buf.rewind();

        aFile.close();
    }
}
