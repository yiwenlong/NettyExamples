package io.github.yiwenlong.logevent.monitor;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: LogEventMonitor <port>");
        }
        Monitor monitor = new Monitor(new InetSocketAddress(Integer.parseInt(args[0])));
        Channel channel = monitor.bind();
        System.out.println("LogEventMonitor running");
        try {
            channel.closeFuture().sync();
        } finally {
            monitor.stop();
        }
    }
}
