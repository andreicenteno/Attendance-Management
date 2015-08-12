package com.am.common;

public enum ResponseCode{
	
	SUCCESSFUL(1000, "OK"),
	EMAIL_EXIST(1001, "Email Exist"),
	INVALID_FORMAT(1002, "Invalid Format Value"),
	REQUIRED_FIELD(1003, "Make sure all required fields filled out correctly"),
	TECHNICAL_ERROR(9999, "Technical Error");
	
	
	private int code;
	private String message;
	
	
	/**
	 * @param code
	 * @param message
	 */
	private ResponseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	/**
	 * @return the code
	 */
	public final int getCode() {
		return code;
	}
	
	/**
	 * @return the message
	 */
	public final String getMessage(String additionalMessage) {
		return message + " : " + additionalMessage;
	}
	
	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}
	
	
}