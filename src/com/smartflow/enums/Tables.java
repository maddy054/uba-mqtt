package com.smartflow.enums;

public enum Tables {
	USER("USER_DETAILS"),
	FLOW("WATER_FLOW_DATA"),
	PROJECT("PROJECT_DETAILS"),
	TANK("TANK_DETAILS"),
	LOCATION("LOCATION_DETAILS"),
	PUMP("PUMP_DETAILS"),
	CONNECTION("CONNECTION_DETAILS"),
	SENSOR_DATA("SENSOR_DATA");
	
	private final String name;
	
	private Tables(String name) {
		this.name = name;
		
	}
	public String get() {
		return name;
	}
}
