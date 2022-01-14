package com.gt.netty.pack;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 粘包
 *
 * @author GTsung
 * @date 2022/1/14
 */
@Slf4j
public class StudyClient {

    public static void main(String[] args) {
        pack1();
    }

    /**
     * 粘包:客戶端發送abc def ---> 接收者收到abcdef  未分開：原因：接收方接收不及時且滑動窗口大導致發送方的消息全部緩存至窗口
     * 半包：發送abcdef ---> 接收者收到abc  def, 原因：接收方窗口接收字節容量小，發送方只能先發送前一部分字節
     */
    private static void pack1() {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    log.debug("connected...");
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            log.debug("sending...");
                            for (int i = 0; i < 10; i++) {
                                ByteBuf byteBuf = ctx.alloc().buffer();
                                byteBuf.writeBytes(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
                                ctx.writeAndFlush(byteBuf);
                            }
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 9001)).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            log.error("client error", e);
        } finally {
            worker.shutdownGracefully();
        }
    }
}
