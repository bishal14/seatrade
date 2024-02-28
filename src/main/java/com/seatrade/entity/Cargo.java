package com.seatrade.entity;


public class Cargo {

	private int id;
	private int sourceHarbourId;
	private int  destinationHarbourId;
	private double value;

	public Cargo(int sourceHarbourId, int destinationHarbourId, double value) {
		this.sourceHarbourId = sourceHarbourId;
		this.destinationHarbourId = destinationHarbourId;
		this.value = value;
	}

	public Cargo(int id, int sourceHarbourId, int destinationHarbourId, double value) {
		this.id = id;
		this.sourceHarbourId = sourceHarbourId;
		this.destinationHarbourId = destinationHarbourId;
		this.value = value;
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
