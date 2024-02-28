package sea;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class CompanyApp extends Receiver{

	Socket clientShips;
	List<Harbour> harbours;
	
	public void refreshData() {
		
	}
	
	public void receiveOrders() {
		
	}
	
	public void manageShip() {
		
	}
	
	public void receiveShip(Ship ship) {
		
	}
	public static void main(String[] args) {
	Receiver r = new Receiver();
	
	try {
		r.socket = new Socket("localhost", 8151);
		r.in = new BufferedReader(new InputStreamReader(r.socket.getInputStream()));
		r.out = new PrintWriter(r.socket.getOutputStream());
		r.out.write("getinfo:cargo");
		String zeile;
		while((zeile = r.in.readLine()) != null) {
			System.out.println(zeile);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void send(String text) {
		
	}
}
