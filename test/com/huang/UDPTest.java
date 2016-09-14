package com.huang;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPTest {
	public static final int DEST_PORT = 800;
	public static final String DEST_IP = "192.168.100.1";

	private DatagramSocket socket = null;
	byte[] inBuff = new byte[1];

	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	private DatagramPacket outPacket = null;

	public void init() throws IOException {
		try {
			socket = new DatagramSocket();
			outPacket = new DatagramPacket(new byte[0], 0, InetAddress
					.getByName(DEST_IP), DEST_PORT);
			Scanner scan = new Scanner(System.in);
			while (scan.hasNextLine()) {
				byte[] buff = scan.nextLine().getBytes();
				outPacket.setData(buff);
				socket.send(outPacket);
				socket.receive(inPacket);
				byte _byte = inBuff[0];
				System.out.println(byteToString(_byte));
			}
		} finally {
			if (socket != null)
				socket.close();
		}
	}

	public static String byteToString(byte b) {
		StringBuffer buf = new StringBuffer();
		buf.append(b);

		return buf.toString();
	}

	public static void main(String[] args) throws IOException {
		new UDPTest().init();
	}
}
