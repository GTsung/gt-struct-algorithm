package com.gt.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.charset.StandardCharsets;

/**
 * @author GTsung
 * @date 2022/1/14
 */
public class ByteBufStudy {

    public static void main(String[] args) {
        // 創建byteBuf
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(16);
        ByteBufUtil.log(buffer);

        // 向buffer中写入数据
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 20; i++) {
            sb.append("a");
        }
        buffer.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8));

        // 查看写入结果
        ByteBufUtil.log(buffer);

        // 默認是池化的直接内存
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(16);
        // PooledUnsafeDirectByteBuf ---> 池化的直接内存
        System.out.println(buf.getClass());
        // 如果在handler中創建buffer，則使用ChannelHandlerContext.alloc().buffer();

        // 池化的堆内存
        buf = ByteBufAllocator.DEFAULT.heapBuffer(16);
        System.out.println(buf.getClass());

        // 直接内存讀寫性能高(少一次内存複製)，但創建和銷毀的代價大，需要配合池化
        // 直接内存對GC壓力小，這部分内存不受JVM垃圾回收的管理，需要及時主動釋放

        // -Dio.netty.allocator.type={unpooled|pooled} 開啓池化參數

        // 寫入
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(16, 20);
        ByteBufUtil.log(byteBuf);
        byteBuf.writeBytes(new byte[] {1, 2, 3, 4});
        ByteBufUtil.log(byteBuf);
        byteBuf.writeInt(5);
        ByteBufUtil.log(byteBuf);
        byteBuf.writeIntLE(6);
        ByteBufUtil.log(byteBuf);
        byteBuf.writeLong(7);
        ByteBufUtil.log(byteBuf);

        // 池化，可以重用池中的ByteBuf實例，節約内存
        // 讀寫指針分離，不需要像ByteBuffer切換讀寫模式
        // 可以自動擴容
        // 零拷貝
    }
}
