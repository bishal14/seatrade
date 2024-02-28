package com.seatrade.entity;

import com.seatrade.Receiver;
import com.seatrade.ShipApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyApp extends Receiver {

	ServerSocket serverSocket;
	List<Harbour> harbours;
	Receiver r = new Receiver();
	String companyName;
	Map<String, ShipApp> registeredShips;

	public CompanyApp(int port, String companyName) {
		this.registeredShips = new HashMap<>();
		this.companyName = companyName;
		 try {
	            this.serverSocket = new ServerSocket(port);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	    
	/*
	public synchronized void registerShip(ShipApp ship) throws IOException {
        registeredShips.put(ship.getShipName(), ship);
    }

    // Methode zur Entfernung eines Schiffs
    public synchronized void unregisterShip(ShipApp ship) {
        registeredShips.remove(ship.ship.id);
    }
*/

    
	public void refreshData() {

	}

	public void receiveOrders() {

	}

	public void manageShip() {

	}

	public void connectToServer() throws Exception {
		try {
			r.socket = new Socket("localhost", 8150);
			r.in = new BufferedReader(new InputStreamReader(r.socket.getInputStream()));
			r.out = new PrintWriter(r.socket.getOutputStream(), true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		r.out.println(message);
	}

	public String receiveMessage() throws IOException {
		return r.in.readLine();
	}

	public void register() throws IOException {
		if (!r.socket.isConnected()) {
			System.out.println("Check Connection");
		}

		sendMessage("register:" + companyName);
		String response = receiveMessage();
		if (response.startsWith("registered")) {
			System.out.println("Registered successfully with SeaTrade server.");
			System.out.println(response);
		} else {
			System.out.println("Error während der Registrierung aufgetreten: " + response);
		}
	}

	public void getInfo(String infoType) throws Exception {
		sendMessage("getinfo:" + infoType);
		String response = receiveMessage();
		while (!response.startsWith("endinfo")) {
			System.out.println(response);
			response = receiveMessage();
		}
	}

	public void run() {
		try {
			connectToServer();
			register();

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String command = "";
			 while (!command.equals("exit")) {
	                System.out.print("Enter command (cargo/getinfo:harbour/getinfo:cargo/exit): ");
	                command = reader.readLine();
	                if (command.equals("exit")) {
	                    sendMessage("exit");
	                    break;
	                } else if (command.startsWith("getinfo")) {
	                    String infoType = command.split(":")[1];
	                    getInfo(infoType);
	                } else {
	                    System.out.println("Invalid command. Please try again.");
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        } finally {
	            try {
	                if (socket != null) {
	                    socket.close();
	                }
	            } catch (IOException e) {
	                System.out.println("Error while closing socket: " + e.getMessage());
	            }
	        }
	    }
	

	public void exit() {
		try {
			if (r.socket != null) {
				r.out.close();
				r.in.close();
				r.socket.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CompanyApp companyApp = new CompanyApp(8082,"TestCompany");
		companyApp.run();
	}

}
