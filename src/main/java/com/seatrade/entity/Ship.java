package com.seatrade.entity;

import com.seatrade.util.position.Position;
import com.seatrade.util.position.RadarScreen;
import com.seatrade.entity.Ship;

import java.util.List;

public class Ship {

	private int id;

	private String name;
	private int cellId;


	private Position position;

	public Ship(int id, String name, int cellId) {
		this.id = id;
		this.name = name;
		this.cellId = cellId;
	}

	public Ship(String name, int cellId) {
		this.name = name;
		this.cellId = cellId;
	}

	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public int getCellId() {
		return cellId;
	}

	public Position getPosition() {
		return position;
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
