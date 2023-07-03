package com.example.demo.exception;

public class UserNotAddedSuccessfully extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotAddedSuccessfully() {
		
	}
	
	public UserNotAddedSuccessfully(String msg) {
		super(msg);
	}
	

}
