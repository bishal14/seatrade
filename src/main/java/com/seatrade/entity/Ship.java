package com.seatrade.entity;

import com.seatrade.ShipState;
import com.seatrade.util.position.Position;
import com.seatrade.util.position.RadarScreen;

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

	public boolean isSameHarbour(Harbour fromHarbour, Harbour toHarbour){

		return fromHarbour.getxPosition()==toHarbour.getxPosition() && fromHarbour.getyPosition()==toHarbour.getyPosition();
	}

	public Harbour findHarbour(int xPosition,int yPosition){
		Harbour harbour = null;

		if(xPosition==25 && yPosition ==9){
			harbour =new Harbour(xPosition,yPosition,"NONE","lisabon");
			this.setHarbour(harbour);
 	        return harbour;
 			}else if(xPosition==24&& yPosition==16){
			harbour =new Harbour(xPosition,yPosition,"NONE","dakar");
			this.setHarbour(harbour);

			return harbour;
		} else if(xPosition==29 && yPosition==13){
			harbour =new Harbour(xPosition,yPosition,"NONE","algier");
			this.setHarbour(harbour);

			return harbour;
		}else if(xPosition== 29&& yPosition==18){
			harbour =new Harbour(xPosition,yPosition,"NONE","cotonau");
			this.setHarbour(harbour);

			return harbour;
		}else if(xPosition== 2&& yPosition==3){
			harbour =new Harbour(xPosition,yPosition,"NONE","halifax");
			this.setHarbour(harbour);

			return harbour;
		}else if(xPosition==29 && yPosition==0){
			harbour =new Harbour(xPosition,yPosition,"NONE","plymouth");
			this.setHarbour(harbour);

			return harbour;
		}else if(xPosition==28 && yPosition==5){
			harbour =new Harbour(xPosition,yPosition,"NONE","brest");
			this.setHarbour(harbour);

			return harbour;
		}else if(xPosition==0 && yPosition==10){
			harbour =new Harbour(xPosition,yPosition,"NONE","ney york");
			this.setHarbour(harbour);

			return harbour;
		}else if(xPosition==2 && yPosition==18){
			harbour =new Harbour(xPosition,yPosition,"NONE","carracas");
			this.setHarbour(harbour);

			return harbour;
		}else if(xPosition== 17&& yPosition==0){
			harbour =new Harbour(xPosition,yPosition,"NONE","reykjavik");
			this.setHarbour(harbour);

			return harbour;
		}
		throw new RuntimeException("Ship is on the way!");
	}
}
