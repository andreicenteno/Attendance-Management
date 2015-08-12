package com.am.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public final class ConvertionUtil{
	
	private static java.sql.Timestamp getCurrentTimeStamp() {
		 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	 
	}
	
	public static void main (String args[]) throws Exception{
		System.out.println("Convertion Util Testing Zone! Run as > Java Application");
	/*	Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentTimeStamp());
		Date dateNow = cal.getTime();
	*/	//System.out.println(convertFormatter1().format(dateNow)+getCurrentTimeStamp());
		Calendar cal = Calendar.getInstance();
		cal.setTime(Timestamp.valueOf("2015-03-02 00:00:00"));
		Date dateNow = cal.getTime();
		System.out.println(convertFormatter1().format(dateNow)+getCurrentTimeStamp());
		String theFirst = "Java Programming";
		String ROM = "\"";
		System.out.println(ROM+theFirst+ROM);
		
	}
	
	public static Date formatDate(String parsigDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(Timestamp.valueOf(parsigDate));
		Date dateNow = cal.getTime();
		return dateNow;
	}
	
	
	public static DateFormat convertFormatter1(){
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter;
	}
	
	public static DateFormat convertFormatter2(){
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		return formatter;
	}
	
	public static DateFormat convertFormatter3(){
		DateFormat formatter = new SimpleDateFormat("MMM d yyyy  hh:mm a");
		return formatter;
	}
	
	
}