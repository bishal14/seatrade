package com.seatrade;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Receiver extends Thread{

	public Socket socket;
	public BufferedReader in;
	public PrintWriter out;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
