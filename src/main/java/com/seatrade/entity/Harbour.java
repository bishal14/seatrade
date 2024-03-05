package com.seatrade.entity;

import com.seatrade.Cell;
import com.seatrade.Direction;

import java.util.List;

public class Harbour {

	private int harbourId;

	private  int xPosition;
	private int yPosition;

	String direction;

	private String name;

	List<Cargo> cargos;
	List<Ship> shipsInHarbour;


	public Harbour(int xPosition, int yPosition, String direction, String name) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.direction = direction;
		this.name = name;
	}

	public Harbour( int xPosition, int yPosition, String direction, String name,int harbourId) {
		this.harbourId = harbourId;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.direction = direction;
		this.name = name;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public int getHarbourId() {
		return harbourId;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public List<Ship> getShipsInHarbour() {
		return shipsInHarbour;
	}
}
