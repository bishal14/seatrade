package sea;

import com.seatrade.Receiver;
import com.seatrade.entity.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ShipApp {

	Receiver seaTradeServer;
	Ship ship;

	public ShipApp(Socket socket) throws IOException {
		seaTradeServer = new Receiver(socket);
	}

	public void launchShip(String companyName, String harbour, String shipName) throws IOException {
		sendMessage("launch:" + companyName + ":" + harbour + ":" + shipName);
		String response = receiveMessage();
		if (response.startsWith("launched")) {
			String[] parts = response.split(":");
			String position = parts[1];
			String cost = parts[2];
			System.out.println("Launched successfully. Position: " + position + ", Cost: " + cost);
		} else if (response.startsWith("error")) {
			System.out.println("Error during ship launch: " + response);
		}
	}

	public void moveToHarbour(String harbour) throws IOException {
		sendMessage("moveto:" + harbour);
		String response = receiveMessage();
		if (response.startsWith("moved")) {
			String[] parts = response.split(":");
			String position = parts[1];
			String cost = parts[2];
			System.out.println("Moved to " + harbour + ". New position: " + position + ", Cost: " + cost);
		} else if (response.startsWith("reached")) {
			System.out.println("Reached " + harbour);
		} else if (response.startsWith("error")) {
			System.out.println("Error during movement: " + response);
		}
	}

	public void loadCargo() throws IOException {
		sendMessage("loadcargo");
		String response = receiveMessage();
		if (response.startsWith("loaded")) {
			String[] parts = response.split(":");
			String cargoInfo = parts[1];
			System.out.println("Cargo loaded: " + cargoInfo);
		} else if (response.startsWith("error")) {
			System.out.println("Error during cargo loading: " + response);
		}
	}

	public void unloadCargo() throws IOException {
		sendMessage("unloadcargo");
		String response = receiveMessage();
		if (response.startsWith("unloaded")) {
			String profit = response.split(":")[1];
			System.out.println("Cargo unloaded. Profit: " + profit);
		} else if (response.startsWith("error")) {
			System.out.println("Error during cargo unloading: " + response);
		}
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

	public String receiveMessage() throws IOException {
		return seaTradeServer.in.readLine();
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String command = "";
			while (!command.equals("exit")) {
				System.out.print("Enter command: ");
				command = reader.readLine();
				if (command.equals("exit")) {
					sendMessage("exit");
					exit();
					break;
				} else if (command.startsWith("launch")) {
					String company = command.split(":")[1];
					System.out.println(company);
					String harbour = command.split(":")[2];
					String shipname = command.split(":")[3];
					System.out.println(shipname);
					launchShip(company, harbour, shipname);

				} else if (command.startsWith("moveto")) {
					String harbour = command.split(":")[1];
					moveToHarbour(harbour);
				} else if (command.startsWith("loadcargo")) {
					loadCargo();
				} else if (command.startsWith("unloadcargo")) {
					unloadCargo();
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
		ShipApp shipApp = new ShipApp(new Socket("localhost", 8151));
		shipApp.run();
	}

}
