package com.seatrade;

import java.net.ServerSocket;

public class RequestListener extends Thread{
int port;
ServerSocket serverSocket;

public RequestListener(int port) {
	this.port = port;
}
@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
