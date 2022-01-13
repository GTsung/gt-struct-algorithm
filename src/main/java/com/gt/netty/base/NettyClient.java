package com.gt.netty.base;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author GTsung
 * @date 2022/1/13
 */
public class NettyClient {

    public static void main(String[] args) throws Exception {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8090));
        // 同步阻塞
//        channelFuture.sync(); // 加上sync才能發送給服務器
//        Channel channel = channelFuture.channel();
//        System.out.println(channel);
//        channel.writeAndFlush("aasdsadasd");

        // 異步
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                // nio綫程連結建立后回調此方法
                Channel channel = future.channel();
                channel.writeAndFlush("hello world");
            }
        });


        // 此处打断点调试，调用 channel.writeAndFlush(...);
//        System.in.read();

        // 同步阻塞關閉channel
        ChannelFuture closeFuture = channelFuture.channel().closeFuture();
        closeFuture.sync();

        // 異步阻塞關閉
        closeFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("處理關閉之後的操作");
            }
        });
    }
}
