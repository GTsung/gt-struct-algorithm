package com.gt.io.split;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileTest {

    public static void main(String[] args) throws Exception {
//        write();
//        write2();

        System.out.println((byte)'\n');

    }

    private static void write2() throws IOException {
        FileInputStream in = new FileInputStream("a.txt");
        FileChannel inChannel = in.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10*1024);
        byteBuffer.remaining();
        int j = 0;
        int i = inChannel.read(byteBuffer);
        while (i != -1) {
            byteBuffer.flip();
            FileOutputStream fileOutputStream = new FileOutputStream("b"+ j++ +".txt");
            FileChannel outChannel = fileOutputStream.getChannel();
            outChannel.write(byteBuffer);
            outChannel.close();
            byteBuffer.clear();
            i = inChannel.read(byteBuffer);
        }

        inChannel.close();
    }

    private static void write() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("a.txt");
        OutputStreamWriter os = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bw = new BufferedWriter(os);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< 10000; i++) {
            if (i % 10 == 0) {
                sb.append("\n");
            }
            sb.append("12&&&&" + i + " ");
        }
        bw.write(sb.toString());

        bw.flush();
        fileOutputStream.close();
        os.close();
        bw.close();
    }
}
