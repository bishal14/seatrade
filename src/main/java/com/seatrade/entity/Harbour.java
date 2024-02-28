package com.seatrade.entity;

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
		return "2";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public List<Ship> getShipsInHarbour() {
		return shipsInHarbour;
	}
}
