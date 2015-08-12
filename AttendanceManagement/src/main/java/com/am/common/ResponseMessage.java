
package com.am.common;


public interface ResponseMessage{
	/*
	 *  	Response Message -  Message that will display on the web application according
	 * 		to the response of the server.
	 * 
	 * */
	
	
	//-- SUCCESS
	String SAVED = "Data successfully saved!";
	String DELETED = "Data successfully deleted!";
	
	
	//-- FAILED
	String TECHNICAL_ERROR = "Oops! Something went wrong. Technical Error!";
	String REQUIRED_FIELD = "Error in saving data! Please make sure all the required fields are filled out correctly.";
	String INVALID_FORMAT= "Error in saving data! Please check your data format.";
	String EMAIL_EXIST= "Email is already exist. Please use other email.";
	
}