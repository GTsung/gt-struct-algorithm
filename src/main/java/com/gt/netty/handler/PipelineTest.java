package com.gt.netty.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author GTsung
 * @date 2022/1/14
 */
@Slf4j
public class PipelineTest {

    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 原有head、tail的handler，自定義handler加在tail前
                        pipeline.addLast("h1", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("-------handler1");
                                // super.channelRead將消息msg傳遞給後面的入站handler
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("h2", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("-------handler2");
                                // channel從最尾部開始找出站
//                                ctx.channel().writeAndFlush(ctx.alloc().buffer()
//                                        .writeBytes("Server...".getBytes(StandardCharsets.UTF_8)));
                                // ctx從這個入站前找出站handler，因此h3\h4不會收到
                                ctx.writeAndFlush(ctx.alloc().buffer()
                                        .writeBytes("Server...".getBytes(StandardCharsets.UTF_8)));
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("h3", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                System.out.println("----handler3");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("h4", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                System.out.println("----handler4");
                                super.write(ctx, msg, promise);
                            }
                        });

                    }
                }).bind(9000);

        // 多個入站的handler，順序h1-->h2
        // 出站的handler，順序是h4-->h3
    }
}
