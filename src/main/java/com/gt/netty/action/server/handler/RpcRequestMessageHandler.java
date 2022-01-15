package com.gt.netty.action.server.handler;

import com.gt.netty.action.message.RpcRequestMessage;
import com.gt.netty.action.message.RpcResponseMessage;
import com.gt.netty.action.server.service.HelloService;
import com.gt.netty.action.server.service.ServiceFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Slf4j
@ChannelHandler.Sharable
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage message) throws Exception {
        RpcResponseMessage response = new RpcResponseMessage();
        response.setSequenceId(message.getSequenceId());
        try {
            // 未使用代理
            HelloService service = (HelloService)
                    ServiceFactory.getService(Class.forName(message.getInterfaceName()));
            Method method = service.getClass().getMethod(message.getMethodName(), message.getParameterTypes());
            Object invoke = method.invoke(service, message.getParameterValue());
            response.setReturnValue(invoke);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = e.getCause().getMessage();
            response.setExceptionValue(new Exception("远程调用出错:" + msg));
        }
        ctx.writeAndFlush(response);
    }
}
