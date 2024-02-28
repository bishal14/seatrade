package com.seatrade;
<<<<<<< HEAD:src/main/java/com/seatrade/RequestListener.java
=======

>>>>>>> c8e6b9cc36783f16613c9565324c84cf65e1e1e3:src/main/java/com/seatrade/RequestListener.java

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
