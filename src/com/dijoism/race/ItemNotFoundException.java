package com.dijoism.race;

public class ItemNotFoundException extends Exception {
	
	static final long serialVersionUID = 12345;
	
	public ItemNotFoundException(String message) {
		super(message);
	}
}
