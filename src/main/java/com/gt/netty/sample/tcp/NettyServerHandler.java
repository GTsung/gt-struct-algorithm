package com.gt.netty.sample.tcp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author GTsung
 * @date 2022/1/3
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 读取客户端发送的数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // TaskQueue
        // 1.自定义任务
        // 2.定时任务
        // 3.非当前Reactor线程调用Channel的方法

        // 1.自定义任务,先执行channelReadComplete方法，10秒后执行此
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端222", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 客户端222发送完毕后15秒之后才会发送333
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(15 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端333", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 2.自定义的定时任务
        ctx.channel().eventLoop().schedule(() -> {
            try {
                Thread.sleep(5 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端444", CharsetUtil.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 5, TimeUnit.SECONDS);

        // 以上为两个Task，逐个执行，但此处的不会被阻塞
        System.out.println("go on...");
//        System.out.println("服务器读取线程:" + Thread.currentThread().getName() +", channel = " + ctx.channel());
//        System.out.println("ctx = " + ctx);

//        Channel channel = ctx.channel();
//        ChannelPipeline channelPipeline = channel.pipeline();

        // 获取消息
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println("客户端发送的数据是" + byteBuf.toString(CharsetUtil.UTF_8));
//        System.out.println("客户地址:" + ctx.channel().remoteAddress());
    }

    // 数据读取完毕后的操作
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据写入缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端111", CharsetUtil.UTF_8));
    }

    // 异常处理

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
