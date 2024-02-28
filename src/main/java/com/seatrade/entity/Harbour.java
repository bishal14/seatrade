package com.seatrade.entity;

import com.seatrade.Cell;

import java.util.List;

public class Harbour {

	private int harbourId;
	private String name;

	private  int cellId;


	List<Cargo> cargos;
	List<Ship> shipsInHarbour;

	public Harbour(String name, int cellId) {
		this.name = name;
		this.cellId = cellId;
	}

	public Harbour(String name) {
		this.name = name;
	}

	public Harbour(int id, String name, int cellId) {
		super();
		this.harbourId = id;
		this.name = name;
		this.cellId = cellId;
	}
	
	public void showHarbourDetails() {
		
	}

	public String checkHarbourState() {
		return "2";
	}

	public int getId() {
		return harbourId;
	}

	public String getName() {
		return name;
	}


	public int getCellId() {
		return cellId;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public List<Ship> getShipsInHarbour() {
		return shipsInHarbour;
	}
}
