package io.github.yiwenlong.logevent.broadcaster;

import java.io.File;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: Broadcaster <port> <logfile>");
        }

        Broadcaster broadcaster = new Broadcaster(
                new InetSocketAddress("255.255.255.255", Integer.parseInt(args[0])),
                new File(args[1])
        );

        try {
            broadcaster.run();
        } finally {
            broadcaster.stop();
        }
    }
}
