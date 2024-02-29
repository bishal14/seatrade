package com.seatrade.entity;


public class Cargo {

	private int id;
	private int sourceHarbourId;
	private int  destinationHarbourId;
	private double value;

	private int fkTransportId;
	private int fkShipId;



	public Cargo(int id, int sourceHarbourId, int destinationHarbourId, double value, int fkTransportId, int fkShipId) {
		this.id = id;
		this.sourceHarbourId = sourceHarbourId;
		this.destinationHarbourId = destinationHarbourId;
		this.value = value;
		this.fkTransportId = fkTransportId;
		this.fkShipId = fkShipId;
	}

	public Cargo(int sourceHarbourId, int destinationHarbourId, double value, int fkTransportId, int fkShipId) {
		this.sourceHarbourId = sourceHarbourId;
		this.destinationHarbourId = destinationHarbourId;
		this.value = value;
		this.fkTransportId = fkTransportId;
		this.fkShipId = fkShipId;
	}

	public int getId() {
		return id;
	}

	public int getSourceHarbourId() {
		return sourceHarbourId;
	}

	public int getDestinationHarbourId() {
		return destinationHarbourId;
	}

	public double getValue() {
		return value;
	}


	public int getFkTransportId() {
		return fkTransportId;
	}

	public int getFkShipId() {
		return fkShipId;
	}

	@Override
	public String toString() {
		return "CARGO|" + id + "|"+ sourceHarbourId +"|"+ destinationHarbourId + "|" +value;
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
