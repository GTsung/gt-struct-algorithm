package com.gt.netty.action.server;

import com.gt.netty.action.protocol.MessageCodecSharable;
import com.gt.netty.action.protocol.ProcotolFrameDecoder;
import com.gt.netty.action.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Slf4j
public class ChatServer {

    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        LoginRequestMessageHandler LOGIN_HANDLER = new LoginRequestMessageHandler();
        ChatRequestMessageHandler CHAT_HANDLER = new ChatRequestMessageHandler();
        GroupCreateRequestMessageHandler GROUP_CREATE_HANDLER = new GroupCreateRequestMessageHandler();
        GroupJoinRequestMessageHandler GROUP_JOIN_HANDLER = new GroupJoinRequestMessageHandler();
        GroupMembersRequestMessageHandler GROUP_MEMBER_HANDLER = new GroupMembersRequestMessageHandler();
        GroupQuitRequestMessageHandler GROUP_QUIT = new GroupQuitRequestMessageHandler();
        GroupChatRequestMessageHandler GROUP_CHAT = new GroupChatRequestMessageHandler();
        QuitHandler QUIT_HANDLER = new QuitHandler();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ProcotolFrameDecoder());
                            ch.pipeline().addLast(LOGGING_HANDLER);
                            ch.pipeline().addLast(MESSAGE_CODEC);
                            // 5s内如果沒有得到channel消息，會觸發一個 IdleState#READER_IDLE 事件
                            ch.pipeline().addLast(new IdleStateHandler(5, 0, 0));
                            // ChannelDuplexHandler可以同時作爲入站、出站處理器
                            ch.pipeline().addLast(new ChannelDuplexHandler() {
                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    IdleStateEvent event = (IdleStateEvent) evt;
                                    if (event.state() == IdleState.READER_IDLE) {
                                        log.debug("已經5s鈡未讀取數據");
                                        ctx.channel().close();
                                    }
                                }
                            });
                            ch.pipeline().addLast(LOGIN_HANDLER);
                            ch.pipeline().addLast(CHAT_HANDLER);
                            ch.pipeline().addLast(GROUP_CREATE_HANDLER);
                            ch.pipeline().addLast(GROUP_JOIN_HANDLER);
                            ch.pipeline().addLast(GROUP_MEMBER_HANDLER);
                            ch.pipeline().addLast(GROUP_QUIT);
                            ch.pipeline().addLast(GROUP_CHAT);
                            ch.pipeline().addLast(QUIT_HANDLER);
                        }
                    });
            Channel channel = bootstrap.bind(9880).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("{}", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
