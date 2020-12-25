package io.github.yiwenlong.logevent.monitor;

import io.github.yiwenlong.logevent.core.LogEvent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {
        String builder = msg.getReceivedTimestamp() +
                " [" +
                msg.getSource().toString() +
                "] [" +
                msg.getLogfile() +
                "] : " +
                msg.getMsg();
        System.out.println(builder);
    }
}
