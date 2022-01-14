package com.gt.netty.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author GTsung
 * @date 2022/1/12
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // DefaultEventLoop只有定时任务和普通任务，可以用它来处理费时任务，腾出worker线程来处理IO
        EventLoopGroup defaultGroup = new DefaultEventLoop();

        // NioEventLoopGroup可以处理IO、定时任务、普通任务
        // 细分为专门负责连接的boss及专门做数据处理的worker
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(2);


        new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("group-01", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                System.out.println(buf.toString(Charset.defaultCharset()));
                                log.info(buf.toString(Charset.defaultCharset()));
                                ctx.fireChannelRead(msg); // 將消息傳遞給下一個handler
                            }
                        })
                                .addLast(defaultGroup, "group-02", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buf = (ByteBuf) msg;
                                        System.out.println(buf.toString(Charset.defaultCharset()));
                                        log.info(buf.toString(Charset.defaultCharset()));
                                    }
                                })

                        ;
                    }
                })
                .bind(8090);
    }
}
