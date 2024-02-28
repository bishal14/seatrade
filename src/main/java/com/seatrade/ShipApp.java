package com.seatrade;

import com.seatrade.entity.CompanyApp;
import com.seatrade.entity.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ShipApp extends Receiver {

	Ship ship;
	CompanyApp companyApp;
	String json;
	Receiver r = new Receiver();
	double balance;
	boolean isConnectedToCompanyApp;
     BufferedReader userInput;


	public ShipApp(CompanyApp companyApp) {
		this.companyApp = companyApp;
		this.isConnectedToCompanyApp = false;
        this.userInput = new BufferedReader(new InputStreamReader(System.in));

	}

	public void registerToCompanyApp() {
		try {
			r.socket = new Socket(companyApp.serverSocket.getInetAddress(), companyApp.serverSocket.getLocalPort());
			r.in = new BufferedReader(new InputStreamReader(companyApp.serverSocket.getInputStream()));
			r.out = new PrintWriter(companyApp.serverSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String recieveInstruction() throws IOException {
		return companyApp.in.readLine();
	}
	
	public void sendMessageToCompanyApp(String text) {
		 companyApp.out.println(text);
	}
	
	public void sendInstruction(String instruction) {
		sendMessageToCompanyApp(ship.id + ":" + instruction);
	}
	
	public void run() {
        try {
            registerToCompanyApp();
            sendMessageToCompanyApp("register:" + ship.id);
            String response = companyApp.receiveMessage();
            if (response.startsWith("registered")) {
                isConnectedToCompanyApp = true;
                System.out.println("Registered successfully with CompanyApp.");
            } else {
                System.out.println("Error occurred during registration: " + response);
                return;
            }

            while (isConnectedToCompanyApp) {
                System.out.print("Enter command (order/exit): ");
                String command = userInput.readLine();
                if (command.equals("order")) {
                    sendMessageToCompanyApp("order");
                    response = receiveMessage();
                    if (response.startsWith("order")) {
                        executeOrder(response);
                    } else {
                        System.out.println("Error occurred during ordering: " + response);
                    }
                } else if (command.equals("exit")) {
                    sendMessageToCompanyApp("exit");
                    isConnectedToCompanyApp = false;
                } else {
                    System.out.println("Invalid command. Please try again.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (companyApp.serverSocket != null) {
                    companyApp.serverSocket.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing socket: " + e.getMessage());
            }
        }
    }
	
	
	 

}
