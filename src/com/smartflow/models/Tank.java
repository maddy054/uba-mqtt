package com.smartflow.models;

public class Tank extends Location{
	private long capacity;
	private int height;
	private long waterAvailability;
	
	
	public long getCapacity() {
		return capacity;
	}
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public long getWaterAvailability() {
		return waterAvailability;
	}
	public void setWaterAvailability(long waterAvailability) {
		this.waterAvailability = waterAvailability;
	}

}
