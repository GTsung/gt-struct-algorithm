package com.gt.io.split;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileTest {

    public static void main(String[] args) throws Exception {
//        write();
//        write2();

//        byteBufferRead();

        write3();
    }

    private static void write3() throws Exception {
        FileInputStream fin = new FileInputStream("a.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*10);
        String lineStr = null;
        int index = 0;
        while ((lineStr = br.readLine()) != null) {
            lineStr = lineStr + "\n";
            byte[] bytes = lineStr.getBytes(StandardCharsets.UTF_8);
            long length = bytes.length;
            if (byteBuffer.remaining() < length) {
                byteBuffer.flip();
                FileOutputStream fileOutputStream = new FileOutputStream("b" + index++ + ".txt");
                FileChannel fileChannel = fileOutputStream.getChannel();
                fileChannel.write(byteBuffer);
                fileOutputStream.close();
                byteBuffer.clear();
            }
            byteBuffer.put(bytes);
        }
        br.close();
        fin.close();
    }

    private static void byteBufferRead() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*10);
        StringBuilder sb = new StringBuilder();
        sb.append("12");
        byteBuffer.put(sb.toString().getBytes(StandardCharsets.UTF_8));
        sb.delete(0, sb.length());
        sb.append("23");
        byteBuffer.put(sb.toString().getBytes(StandardCharsets.UTF_8));
        byteBuffer.rewind();
        byte[] bytes = new byte[1024];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
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
