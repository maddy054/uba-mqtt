package com.smartflow.models;

public class Flow {
	private int connectionId;
	private long fromTime;
	private long toTime;
	private int fromValue;
	private int toValue;
	private int leakage;
	public int getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	public long getFromTime() {
		return fromTime;
	}
	public void setFromTime(long fromTime) {
		this.fromTime = fromTime;
	}
	public long getToTime() {
		return toTime;
	}
	public void setToTime(long toTime) {
		this.toTime = toTime;
	}
	public int getFromValue() {
		return fromValue;
	}
	public void setFromValue(int fromValue) {
		this.fromValue = fromValue;
	}
	public int getToValue() {
		return toValue;
	}
	public void setToValue(int toValue) {
		this.toValue = toValue;
	}
	public int getLeakage() {
		return leakage;
	}
	public void setLeakage(int leakage) {
		this.leakage = leakage;
	}
}
