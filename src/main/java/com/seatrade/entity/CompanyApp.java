package com.seatrade.entity;

import com.seatrade.Receiver;
import com.seatrade.dao.CompanyDaoImplementation;
import com.seatrade.dao.HarbourDaoImplementation;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class CompanyApp implements MessageHandler{

	String companyName;
	Receiver seaTradeServer;

	public CompanyApp(Socket socket, String companyName) throws IOException {
		this.companyName = companyName;
		seaTradeServer = new Receiver(socket);
	}

	public void registerToSeaTradeServer() throws IOException {
		if (!seaTradeServer.socket.isConnected()) {
			System.out.println("Check Connection");
		}

		sendMessage("register:" + companyName);

	}

	public void getInfo(String infoType) throws IOException {
		sendMessage("getinfo:" + infoType);

	}

	public void exit() {
		try {
			if (seaTradeServer.socket != null) {
				seaTradeServer.out.close();
				seaTradeServer.in.close();
				seaTradeServer.socket.close();
				seaTradeServer.interrupt();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		seaTradeServer.out.println(message);
	}


	public void run() {
		try {
			registerToSeaTradeServer();

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String command = "";
			seaTradeServer.start();
			while (!command.equals("exit")) {
				System.out.print("Enter command (cargo/getinfo:harbour/getinfo:cargo/exit): ");
				command = reader.readLine();
				if (command.equals("exit")) {
					sendMessage("exit");
					exit();
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
				if (seaTradeServer.socket != null) {
					seaTradeServer.socket.close();
				}
			} catch (IOException e) {
				System.out.println("Error while closing socket: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		CompanyApp companyApp = new CompanyApp(new Socket("localhost", 8150),"TestCompany");
		companyApp.run();
	}

	@Override
	public void handleMessage(String message) {
		System.out.println("Handling message in ShipApp: " + message);


	}

	public String receiveMessage() {
		return "";
	}

	public Company registerCompany(String companyName){
		Company company = new Company(companyName);
		CompanyDaoImplementation companyDaoImplementation = new CompanyDaoImplementation();
		companyDaoImplementation.add(company);
		return company;
	}


	public Harbour addHarbour( ){
		Harbour harbour1 = new Harbour(25,9,"NONE","lissabon");
		Harbour harbour2 = new Harbour(24,16,"NONE","dakar");
		Harbour harbour3 = new Harbour(29,13,"NONE","algier");
		Harbour harbour4 = new Harbour(29,18,"NONE","cotonau");
		Harbour harbour5 = new Harbour(2,3,"NONE","halifax");
		Harbour harbour6 = new Harbour(29,0,"NONE","plymouth");
		Harbour harbour7 = new Harbour(28,5,"NONE","brest");
		Harbour harbour8 = new Harbour(0,10,"NONE","ney york");
		Harbour harbour9 = new Harbour(2,18,"NONE","carracas");

		HarbourDaoImplementation harbourDaoImplementation = new HarbourDaoImplementation();
		harbourDaoImplementation.add(harbour1);
		harbourDaoImplementation.add(harbour2);
		harbourDaoImplementation.add(harbour3);
		harbourDaoImplementation.add(harbour4);
		harbourDaoImplementation.add(harbour5);
		harbourDaoImplementation.add(harbour6);
		harbourDaoImplementation.add(harbour7);
		harbourDaoImplementation.add(harbour8);
		harbourDaoImplementation.add(harbour9);


		return  harbour1;
	}






}
