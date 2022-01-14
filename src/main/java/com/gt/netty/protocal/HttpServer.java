package com.gt.netty.protocal;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;

/**
 * HTTP協議
 * @author GTsung
 * @date 2022/1/14
 */
@Slf4j
public class HttpServer {

    public static void main(String[] args) {
        new ServerBootstrap().group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        // 作爲服務器，使用HttpServerCodec作爲編碼器和解碼器
                        ch.pipeline().addLast(new HttpServerCodec());
                        // 服務器只處理HttpRequest
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
                                System.out.println(msg.uri());
                                // 獲得完整響應，設置版本號與狀態碼
                                DefaultFullHttpResponse response = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
                                // 設置響應内容
                                byte[] bytes = "<h1>Hello,world</h1>".getBytes(StandardCharsets.UTF_8);
                                // 設置響應體長度，避免瀏覽器一直接收響應内容
                                response.headers().setInt(CONTENT_LENGTH, bytes.length);
                                // 設置響應躰
                                response.content().writeBytes(bytes);
                                ctx.writeAndFlush(response);
                            }
                        });

                    }
                }).bind(8900);
    }
}
