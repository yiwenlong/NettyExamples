package io.github.yiwenlong.logevent.broadcaster;

import java.io.File;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: Broadcaster <ip:port> <logfile>");
        }
        final String[] ipPort = args[0].split(":");
        Broadcaster broadcaster = new Broadcaster(
                new InetSocketAddress(ipPort[0], Integer.parseInt(ipPort[1])),
                new File(args[1])
        );

        try {
            broadcaster.run();
        } finally {
            broadcaster.stop();
        }
    }
}
