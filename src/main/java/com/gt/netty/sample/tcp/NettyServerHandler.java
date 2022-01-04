package com.gt.netty.sample.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @author GTsung
 * @date 2022/1/3
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 读取客户端发送的数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器读取线程:" + Thread.currentThread().getName() +", channel = " + ctx.channel());
        System.out.println("ctx = " + ctx);

        Channel channel = ctx.channel();
        ChannelPipeline channelPipeline = channel.pipeline();

        // 获取消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的数据是" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户地址:" + ctx.channel().remoteAddress());
    }

    // 数据读取完毕后的操作
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据写入缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端", CharsetUtil.UTF_8));
    }

    // 异常处理

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
