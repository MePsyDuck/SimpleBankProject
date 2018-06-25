package com.bank;

@SuppressWarnings("serial")
public class EntityNotFoundException extends Exception {
	public EntityNotFoundException(String message) {
		super(message + " : Not Found");
	}
}
