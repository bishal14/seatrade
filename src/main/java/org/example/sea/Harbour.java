package org.example.sea;

import java.util.List;

public class Harbour {

	int id;
	String name;
	String position;
	List<Cargo> cargos;
	List<Ship> shipsInHarbour;
	
	
	public Harbour(int id, String name, String position) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
	}
	
	public void showHarbourDetails() {
		
	}

	public String checkHarbourState() {
		return "";
	}
	
}
