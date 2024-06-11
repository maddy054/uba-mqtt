package com.smartflow.models;

public class Sensor {
	private int locationId;
	private long time;
	private String value;
	private int sensorType;
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String string) {
		this.value = string;
	}
	public int getParameter() {
		return sensorType;
	}
	public void setParameter(int parameter) {
		this.sensorType = parameter;
	}
	
}
