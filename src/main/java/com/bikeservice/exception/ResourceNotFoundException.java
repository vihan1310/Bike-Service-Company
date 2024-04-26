package com.bikeservice.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Bike already registered with the given no.");
		
	}

	public ResourceNotFoundException(String message) {
		super(message);
		
	}
	
	

}
