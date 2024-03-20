package com.seatrade.entity;


public class Cargo {

	private int cargoId;
	private String sourceHarbour;
	private String  destinationHarbour;
	private double value;
	private int fkTransportId;
	private int fkShipId;

	private  int fkSourceHarbourId;

	public Cargo(String sourceHarbour, String destinationHarbour, double value) {
		this.sourceHarbour = sourceHarbour;
		this.destinationHarbour = destinationHarbour;
		this.value = value;
	}

	public Cargo(String sourceHarbour, String destinationHarbour,double value,int fkSourceHarbourId, int cargoId) {
		this.cargoId = cargoId;
		this.sourceHarbour = sourceHarbour;
		this.destinationHarbour = destinationHarbour;
		this.value = value;
		this.fkSourceHarbourId = fkSourceHarbourId;
	}

	public Cargo(int cargoId, String sourceHarbour, String destinationHarbour, double value, int fkTransportId, int fkShipId) {
		this.cargoId = cargoId;
		this.sourceHarbour = sourceHarbour;
		this.destinationHarbour = destinationHarbour;
		this.value = value;
		this.fkTransportId = fkTransportId;
		this.fkShipId = fkShipId;
	}

	public int getCargoId() {
		return cargoId;
	}

	public String getSourceHarbour() {
		return sourceHarbour;
	}

	public String getDestinationHarbour() {
		return destinationHarbour;
	}

	public double getValue() {
		return value;
	}

	public int getFkTransportId() {
		return fkTransportId;
	}

	public int getFkSourceHarbourId() {
		return fkSourceHarbourId;
	}

	public void setFkSourceHarbourId(int fkSourceHarbourId) {
		this.fkSourceHarbourId = fkSourceHarbourId;
	}

	public int getFkShipId() {
		return fkShipId;
	}

	@Override
	public String toString() {
		return "CARGO|" + cargoId + "|"+ sourceHarbour +"|"+ destinationHarbour + "|" +value;
	}
	
	public static Cargo parse(String s){
		// TODO- this method has to be edited once again!
		String[] token = s.trim().split("\\|");
		if(token.length == 5){
			if (token[0].equals("CARGO")){
				int id = Integer.parseInt(token[1]);
				int wert = Integer.parseInt(token[4]);
				return  null;
						//new Cargo(id, token[2], token[3], wert);
			}
		}
		return null;
	}

}
