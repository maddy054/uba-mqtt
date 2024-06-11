package com.smartflow.models;

public class Pump extends Location{

	private String powerRating;
	private String voltageRating;
	private String pumpType;
	

	public String getPowerRating() {
		return powerRating;
	}
	public void setPowerRating(String powerRating) {
		this.powerRating = powerRating;
	}
	public String getVoltageRating() {
		return voltageRating;
	}
	public void setVoltageRating(String voltageRating) {
		this.voltageRating = voltageRating;
	}
	public String getPumpType() {
		return pumpType;
	}
	public void setPumpType(String pumpType) {
		this.pumpType = pumpType;
	}

}
