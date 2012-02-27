package com.gsn.engine.mercurry;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.JSONObject;

import android.util.Log;

public class MercuryClient {
	public interface IMercuryListener {
		void onConnected();

		void onDisconnected();

		void onReceived(String s);

		void onReceivedJson(JSONObject json);
	}

	private class MyThread implements Runnable {
		@Override
		public void run() {

			try {
				// Create the selector
				selector = Selector.open();
				// Creating a Non-Blocking Socket.
				sChannel = createSocketChannel(host, port);
				// Register the channel with selector, listening for all events
				sChannel.register(selector, sChannel.validOps());
				while (!stop) {
					// Wait for an event
					if (selector.select() == 0)
						continue;
					// Get list of selection keys with pending events
					Iterator<SelectionKey> it = selector.selectedKeys().iterator();
					// Process each key at a time
					while (it.hasNext()) {
						// Get the selection key
						SelectionKey selKey = it.next();

						// Remove it from the list to indicate that it is being
						// processed
						it.remove();
						processSelectionKey(selKey);
						// Handle error with channel and unregister
					}

					Thread.sleep(200);

					// selector.close();

					// TODO Auto-generated catch block
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (listener != null && !stop)
					listener.onDisconnected();
				sChannel.close();
				selector.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	public static MercuryClient m;

	public static void main(String[] args) {
		System.out.println("chay...");
		m = new MercuryClient("118.102.3.16", 443, new IMercuryListener() {

			@Override
			public void onConnected() {
				// TODO Auto-generated method stub
				System.out.println("connect");
				String s = "{\"params\":{\"username\":\"1F018988C21ABDC001DA3485\"},\"_cmd\":\"login\",\"ext\":\"caro\"}";
				m.send(s);
			}

			@Override
			public void onDisconnected() {
				// TODO Auto-generated method stub
				System.out.println("disconnect");
			}

			@Override
			public void onReceived(String s) {
				// TODO Auto-generated method stub
				// System.out.println("receive: " + s);
				String gui = "{\"params\":null,\"_cmd\":\"GUI\",\"ext\":\"caro\"}";
				if (s.contains("loginOK")) {

					m.send(gui);
				} else if (s.contains("GUI")) {
					// m.disconnect();
					m.send("asa");
					m.send("gui");
				}
			}

			@Override
			public void onReceivedJson(JSONObject json) {
				// TODO Auto-generated method stub
				System.out.println("receive JSON: " + json);
			}
		});
		m.connect();
		while (true)
			;
	}

	private ByteBuffer buf = ByteBuffer.allocateDirect(1024);

	private String host;

	private IMercuryListener listener;

	private int port;

	private Queue<String> queue = new LinkedBlockingQueue<String>();

	SocketChannel sChannel = null;

	Selector selector = null;

	private boolean stop;

	private Thread thread;

	public MercuryClient(String host, int port, IMercuryListener listener) {
		this.listener = listener;
		this.host = host;
		this.port = port;
	}

	public void connect() {
		stop = false;
		thread = new Thread(new MyThread());
		thread.setDaemon(true);
		thread.start();
	}

	private byte[] convertToBytes(String s) throws Exception {
		byte[] bytes = s.getBytes("UTF-8");
		buf.putShort((short) bytes.length);
		buf.put(bytes);
		buf.flip();
		byte[] dst = new byte[buf.remaining()];
		buf.get(dst);
		buf.clear();
		return dst;

	}

	public SocketChannel createSocketChannel(String hostName, int port) throws IOException {
		// Create a non-blocking socket channel
		SocketChannel sChannel = SocketChannel.open();
		sChannel.configureBlocking(false);

		// Send a connection request to the server; this method is non-blocking
		sChannel.connect(new InetSocketAddress(hostName, port));
		return sChannel;
	}

	public void disconnect() {
		if (stop)
			return;
		stop = true;
		try {
			sChannel.close();
			selector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processSelectionKey(SelectionKey selKey) throws Exception {
		// Since the ready operations are cumulative,
		// need to check readiness for each operation
		if (selKey.isValid() && selKey.isConnectable()) {
			// Get channel with connection request
			SocketChannel sChannel = (SocketChannel) selKey.channel();

			boolean success = sChannel.finishConnect();
			if (!success) {
				// An error occurred; handle it
				// Unregister the channel with this selector
				selKey.cancel();
			} else {
				if (listener != null)
					listener.onConnected();
			}
		}
		if (selKey.isValid() && selKey.isReadable()) {
			// Get channel with bytes to read
			SocketChannel sChannel = (SocketChannel) selKey.channel();
			read(sChannel);
			// See Reading from a SocketChannel
		}
		if (selKey.isValid() && selKey.isWritable()) {
			// Get channel that's ready for more bytes
			SocketChannel sChannel = (SocketChannel) selKey.channel();
			if (!queue.isEmpty())
				write(sChannel);
			// See Writing to a SocketChannel
		}
	}

	private void read(SocketChannel socketChannel) throws Exception {
		// TODO Auto-generated method stub

		// Clear the buffer and read bytes from socket
		buf.clear();
		int numBytesRead = socketChannel.read(buf);

		if (numBytesRead == -1) {
			// No more bytes can be read from the channel
			socketChannel.close();
			if (listener != null && !stop)
				listener.onDisconnected();
		} else {
			// To read the bytes, flip the buffer
			buf.flip();

			// Read the bytes from the buffer ...;
			// see Getting Bytes from a ByteBuffer
			byte[] dst = new byte[buf.remaining()];
			buf.get(dst);
			buf.clear();
			String s = new String(dst);
			if (listener != null) {
				listener.onReceived(s);
				String[] arr = s.split("\0");
				for (int i = 0; i < arr.length; i++) {
					arr[i].trim();
					try {
						if (arr[i].length() > 0) {
							JSONObject json = new JSONObject(arr[i]);
							listener.onReceivedJson(json);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void send(JSONObject json) {
		Log.i("send", "json: " + json);
		send(json.toString());
	}

	public void send(String s) {
		queue.add(s);
	}

	public void setListener(IMercuryListener listener) {
		this.listener = listener;
	}

	private void write(SocketChannel socketChannel) throws Exception {
		// TODO Auto-generated method stub
		String s = queue.poll();
		if (s == null)
			return;
		// System.out.println("write : " + s);

		// Fill the buffer with the bytes to write;
		// see Putting Bytes into a ByteBuffer
		byte[] b = convertToBytes(s);
		buf.put(b);
		// Prepare the buffer for reading by the socket
		buf.flip();
		// Write bytes
		int numBytesWritten = socketChannel.write(buf);
		buf.clear();
	}
}
