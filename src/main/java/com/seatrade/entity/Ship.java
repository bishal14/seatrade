package com.seatrade.entity;

import com.seatrade.util.position.Position;
import com.seatrade.util.position.RadarScreen;
import com.seatrade.entity.Ship;

import java.util.List;

public class Ship {

	int id;
	Position position;
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
