package com.smartflow.utilities;

public class OwnException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public OwnException(String message) {
		super(message);
	}
	public OwnException(String message,	Exception e) {
		super(message,e); 
	}

}
