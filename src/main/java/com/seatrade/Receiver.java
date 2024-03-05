package com.seatrade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Receiver extends Thread {

	public Socket socket;
	public BufferedReader in;
	public PrintWriter out;

	public Receiver(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

	}

	protected void handleMessage(String message) {
		System.out.println("Received: " + message);
	}

	@Override
	public void run() {
		try {
			String message;
			while ((message = in.readLine()) != null) {
				handleMessage(message);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
