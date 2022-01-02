package com.gt.nio.tools;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author GTsung
 * @date 2021/12/24
 */
public class PipeDemo {

    // ThreadA ---> Pipe(SinkChannel) ---> Pipe(SourceChannel) ---> ThreadB
    // Pipe 可以用于多线程之间的数据传输
    public static void main(String[] args) throws Exception {
        Pipe pipe = Pipe.open();
        // 写数据到管道
        Pipe.SinkChannel sinkChannel = pipe.sink();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String msg = "Fuck LiuXiao's mother and wife every time" + System.currentTimeMillis();
        buffer.clear();
        buffer.put(msg.getBytes());
        buffer.flip();

        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }

        // 读取数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = sourceChannel.read(buf);
        System.out.println(new String(buf.array(), 0, bytesRead));

        sourceChannel.close();
        sinkChannel.close();
    }

}
