package org.example.sea;


public class Cargo {

	private int id;
	private Harbour sourceHarbour;
	private Harbour destinationHarbour;
	private double value;
	
	public Cargo(Harbour source, Harbour destinationHarbour, double value) {
		super();
		this.sourceHarbour = sourceHarbour;
		this.destinationHarbour = destinationHarbour;
		this.value = value;
 	}
	
	public Cargo(int id, Harbour source, Harbour destinationHarbour, double value) {
		super();
		this.sourceHarbour = source;
		this.destinationHarbour = destinationHarbour;
		this.value = value;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public Harbour getSourceHarbour() {
		return sourceHarbour;
	}

	public Harbour getDestinationHarbour() {
		return destinationHarbour;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "CARGO|" + id + "|"+ sourceHarbour +"|"+ destinationHarbour + "|" +value;
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
