package com.seatrade.entity;

import com.seatrade.util.position.Position;
import com.seatrade.util.position.RadarScreen;

import java.util.List;

public class Ship {

	private int shipId;
	private int xPosition;
	private int yPosition;
	private  String direction;
	private  double cost;

	private String name;
	private int fkCompanyId;

	public Ship(int id, int xPosition, int yPosition, String direction, double cost, String name) {
		this.shipId = id;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.direction = direction;
		this.cost = cost;
		this.name = name;
	}

	public Ship(String name) {
		this.name = name;
		this.xPosition= 30;
		this.yPosition= 5;
		this.direction= "NONE";
		this.cost=(int)(Math.random())*20000;
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

	public double getCost() {
		return cost;
	}

	public Ship(int id, int xPosition, int yPosition, String direction, double cost, String name, int fkCompanyId) {
		this.shipId = id;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.direction = direction;
		this.cost = cost;
		this.name = name;
		this.fkCompanyId = fkCompanyId;
	}
	public int getFkCompanyId() {
		return fkCompanyId;
	}
	public Ship(int id, String name, int fkCompanyId, int cellId) {
		this.shipId = id;
		this.fkCompanyId=fkCompanyId;
		this.name = name;
 	}

	public Ship(String name,int fkCompanyId, int cellId) {
		this.name = name;
		this.fkCompanyId=fkCompanyId;

 	}

	public int getShipId() {
		return shipId;
	}


	public String getName() {
		return name;
	}



	public RadarScreen getRadar() {
		return radar;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	RadarScreen radar;
	List<Cargo> cargos;
	
	public void recieveCargo(Cargo cargo){
		cargos.add(cargo);
	}
	
	public void moveTo(Harbour harbour) {
		
	}
	
	public void manualControl() {
		
	}
	
	public void performRadarScan() {
		
	}
	
	public void refreshPosition(Position position) {
		
	}
	public void reportStateToCompany(CompanyApp company) {
		
	}
	
	public boolean unloadCargo(Cargo cargo) {
		for (Cargo carg : cargos) {
			if(carg.equals(cargo)) {
				cargos.remove(cargo);
				return true;
			}
		}
		return false;
}
	//hibernate
}
