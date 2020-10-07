package com.tce.nio;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTest {
    public static void main(String[] args) {
        try{
            //创建管道
            Pipe pipe = Pipe.open();
            //访问sink通道
            Pipe.SinkChannel sinkChannel=pipe.sink();
            //通过调用SinkChannel的write()方法，将数据写入SinkChannel
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());

            buf.flip();

            while(buf.hasRemaining()) {
                sinkChannel.write(buf);
            }
        }catch (IOException e){}

        try{
            Pipe pipe = Pipe.open();
            //从读取管道的数据，需要访问source通道
            Pipe.SourceChannel sourceChannel = pipe.source();
            ByteBuffer buf = ByteBuffer.allocate(48);
            //调用source通道的read()方法来读取数据
            //read()方法返回的int值会告诉我们多少字节被读进了缓冲区
            int bytesRead = sourceChannel.read(buf);

        }catch (IOException e){}


    }
}
