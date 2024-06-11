package com.smartflow.models;

public class LineConnection {
	private int connectionId;
	private int fromLocation;
	private int toLocation;
	private int projectId;
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	public int getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(int fromLocation) {
		this.fromLocation = fromLocation;
	}
	public int getToLocation() {
		return toLocation;
	}
	public void setToLocation(int toLocation) {
		this.toLocation = toLocation;
	}

}
