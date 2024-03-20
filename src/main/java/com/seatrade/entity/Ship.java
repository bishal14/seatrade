package com.seatrade.entity;

import com.seatrade.ShipState;
import com.seatrade.util.position.Position;
import com.seatrade.util.position.RadarScreen;

import java.util.List;

public class Ship {

	private int shipId;
	private int xPosition;
	private int yPosition;
	private  String direction;
	private  double cost;
	private Cargo cargo;
	private  Harbour harbour;

	private String name;
	private int fkCompanyId;

	private ShipState shipState;

	private boolean isCargoLoaded;
	public Ship(int id, int xPosition, int yPosition, String direction, double cost, String name) {
		this.shipId = id;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.direction = direction;
		this.cost = cost;
		this.name = name;
		this.isCargoLoaded =false;

	}

	public Ship(String name) {
		this.name = name;
		this.xPosition= 30;
		this.yPosition= 5;
		this.direction= "NONE";
		this.cost=(int)(Math.random())*20000;
		this.shipState=ShipState.WAIT;
		this.cargo = null;
	}

	public Harbour getHarbour() {
		return harbour;
	}

	public void setHarbour(Harbour harbour) {
		this.harbour = harbour;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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

	public Ship(int xPosition, int yPosition, String direction, double cost, String name, int fkCompanyId) {
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

	public boolean isCargoLoaded() {
		return isCargoLoaded;
	}

	public void setCargoLoaded(boolean cargoLoaded) {
		isCargoLoaded = cargoLoaded;
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


	RadarScreen radar;


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
	
	public void unloadCargo() {
		this.setCargo(null);

	}

	public void loadCargo(Cargo cargo){
		if(this.getCargo()==null){
			this.setCargo(cargo);

		}
	}


	public void setState(ShipState shipState) {
		this.shipState = shipState;
	}

	public ShipState getShipState() {
		return shipState;
	}

	public boolean checkFromToHarbour(Harbour fromHarbour, Harbour toHarbour){

		return fromHarbour.getxPosition()==toHarbour.getxPosition() && fromHarbour.getyPosition()==toHarbour.getyPosition();
	}
}
