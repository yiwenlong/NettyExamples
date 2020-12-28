package io.github.yiwenlong.plain;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class PlainNioServer {

	public void serve(int port) throws Exception {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		ServerSocket sSocket = serverChannel.socket();

		InetSocketAddress address = new InetSocketAddress(port);
		sSocket.bind(address);

		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes(StandardCharsets.UTF_8));
		for (;;) {
			selector.select();
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			final Iterator<SelectionKey> iterator = readyKeys.iterator();
			for (SelectionKey readyKey : readyKeys) {
				iterator.remove();
				if (readyKey.isAcceptable()) {
					ServerSocketChannel sChannel = (ServerSocketChannel) readyKey.channel();
					try {
						SocketChannel client = sChannel.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
						System.out.println("Accept connection from " + client);
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
				if (readyKey.isWritable()) {
					SocketChannel client = (SocketChannel) readyKey.channel();
					ByteBuffer buffer = (ByteBuffer) readyKey.attachment();
					while (buffer.hasRemaining()) {
						try {
							if (client.write(buffer) == 0) {
								break;
							}
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
					try {
						client.close();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			throw new IllegalArgumentException("Usage: PainNioServer <port>");
		}
		new PlainNioServer().serve(Integer.parseInt(args[0]));
	}
}
