package com.am.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import com.am.common.Constant;
import com.am.domain.GroupCode;

public class ReadCVS {

  private static String IMPORT_CSV_FORMAT[] = {"first_name","last_name","middle_name","address","contact_number","birthday","gender","group_id"};
	
  public static void main(String[] args) {

	ReadCVS obj = new ReadCVS();
	obj.run();

  }

  public void run() {

	String csvFile = "/Users/User/Desktop/Attendance Management Files/files/AttendeesRecordImport.csv";
	
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	int LINE_RECORD = 0;
	Boolean checkFormat = true;
	
	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
		LINE_RECORD++;
		// use comma as separator
		String[] record = line.split(cvsSplitBy);
		//-- check first the format of .csv file.
		if(checkFormat){
			String result = checkCsvFileFormat(record);
			if(result.equals("OK"))
				System.out.println(result);
			else{
				System.out.println("FIELD: "+Constant.QUOTE+result+Constant.QUOTE+" - FORMAT ERROR! Please check the right format in uploading attendees records");
				break;
			}
			checkFormat = false;
		}else{
			if(record.length>0){
				if(checkRequireField(record)){
					if(isValidDate(record[5])){
						System.out.println("Record Value: "+Constant.QUOTE+record[5]+Constant.QUOTE+" is VALID DATE - PASSED | LINE NUMBER: "+LINE_RECORD);
						if(isBoolean(record[6])){
							System.out.println("Record Value: "+Constant.QUOTE+record[6]+Constant.QUOTE+" is VALID GENDER - PASSED | LINE NUMBER: "+LINE_RECORD);	
							if(checkGroup(record[7])){
								System.out.println("Record Value: "+Constant.QUOTE+record[7]+Constant.QUOTE+" is VALID GROUP - PASSED | LINE NUMBER: "+LINE_RECORD);
							}else{
								System.out.println("Record Value: "+Constant.QUOTE+record[7]+Constant.QUOTE+" is NOT VALID GROUP - FAILED | LINE NUMBER: "+LINE_RECORD);
								break;
							}
						}else{
							System.out.println("Record Value: "+Constant.QUOTE+record[6]+Constant.QUOTE+" is NOT VALID GENDER - FAILED | LINE NUMBER: "+LINE_RECORD);
							break;
						}
					}else{
						System.out.println("Record Value: "+Constant.QUOTE+record[5]+Constant.QUOTE+" is NOT VALID DATE - FAILED | LINE NUMBER: "+LINE_RECORD);
						break;
					}
				}else{
					System.out.println("Error! No value on REQUIRED FIELDS | "+ " LINE NUMBER: "+LINE_RECORD);
					break;
				}
			}else{
				System.out.println("FINISH RECORD");
			}
		  }
	
		}// --end of while
		
		
	

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("DONE IN VALIDATING CSV FILE");
	int TOTAL = LINE_RECORD-1;
	System.out.println("Total of records: "+TOTAL);
  }
  
/*  -- OK -> pass the validation
 *  -- NUMBER_OF_FIELD -> number of field error
 *  -- RETURN FIELD VALUE ==> field is not the same on the needed format.
 * 
 * */
 
  
  
  private static Boolean checkGroup(String group_id){
	  try{
		  if(StringUtils.isNotEmpty(group_id)){
			  long id = Long.parseLong(group_id);
			  if(GroupCode.KKB.getGroup().getId() == id || GroupCode.YAM.getGroup().getId() == id
					  || GroupCode.CHILDREN.getGroup().getId() == id || GroupCode.MEN.getGroup().getId() == id
					  || GroupCode.WOMEN.getGroup().getId() == id)
				  		return true;
			  else
				  return false;  
		  }else{
			  return false;
		  }
	  }catch(Exception e){
		return false;  
	  }
  }
  
  
  
  
  public static boolean isBoolean(String isGender) {
	    try {
	    	if("true".equals(isGender.toLowerCase()) || "false".equals(isGender.toLowerCase())){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    } catch (Exception pe) {
	    	return false;
	    }
	    
	  }
  
  
  public static boolean isValidDate(String inDate) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd");
	    dateFormat.setLenient(false);
	    try {
	      dateFormat.parse(inDate.trim());
	    } catch (ParseException pe) {
	    	try {
				if(dateFormat2.parse(inDate.trim()) != null){
					return true;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	      
	    }
	    return true;
	  }
  
  
  private static Boolean checkRequireField(String record[]){
	  Boolean checkField = true;
	  if(StringUtils.isNotEmpty(record[0]) && StringUtils.isNotEmpty(record[1]) &&
			  StringUtils.isNotEmpty(record[3]) && StringUtils.isNotEmpty(record[4]) && StringUtils.isNotEmpty(record[5])
			  && StringUtils.isNotEmpty(record[6]) && StringUtils.isNotEmpty(record[7]))
		  checkField = true;
		else
		  checkField = false;
	  
	  return checkField;  
  }
  
  private static String checkCsvFileFormat(String record[]){
	  String validateResult="OK";
	  	if(IMPORT_CSV_FORMAT.length == record.length){
	  		for(int i=0; i<record.length;i++){
	  			if(record[i].equals(IMPORT_CSV_FORMAT[i])){
	  				continue;
	  			}else{
	  				validateResult = record[i];
	  				break;
	  			}
	  		}
	  	}else{
	  		validateResult = "NUMBER_OF_FIELD";
	  	}
	  	return validateResult;
  }
  

  public static Boolean  ValidateCsvFile(String filePath, ModelMap modelMap){
	  	Boolean isPassed = true;
	  	BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int LINE_RECORD = 0;
		Boolean checkFormat = true;
		String LOGS_INFORMATION = "", LOGS_ERROR = "", LOGS_SUMMARY = "";
		String RECORD_VALUE = "--> Record Value: ";
		try {

			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
			LINE_RECORD++;
			// use comma as separator
			String[] record = line.split(cvsSplitBy);
			//-- check first the format of .csv file.
			if(checkFormat){
				String result = checkCsvFileFormat(record);
				if(result.equals("OK")){
					LOGS_INFORMATION += "FIELD FORMAT - PASSED"+Constant.BREAK;
				}
				else{
					LOGS_ERROR += "FIELD: "+Constant.QUOTE+result+Constant.QUOTE+" - FORMAT ERROR! Please check the right format in uploading attendees records"+Constant.BREAK;
					isPassed = false;
					//break;
				}
				checkFormat = false;
			}else{
				System.out.println(record.length);
				if(record.length == 8){
					if(checkRequireField(record)){
						if(isValidDate(record[5])){
							LOGS_INFORMATION += RECORD_VALUE+Constant.QUOTE+record[5]+Constant.QUOTE+" is VALID DATE - PASSED | LINE NUMBER: "+LINE_RECORD+Constant.BREAK;
							if(isBoolean(record[6])){
								LOGS_INFORMATION += RECORD_VALUE+Constant.QUOTE+record[6]+Constant.QUOTE+" is VALID GENDER - PASSED | LINE NUMBER: "+LINE_RECORD+Constant.BREAK;
								if(checkGroup(record[7])){
									LOGS_INFORMATION += RECORD_VALUE+Constant.QUOTE+record[7]+Constant.QUOTE+" is VALID GROUP - PASSED | LINE NUMBER: "+LINE_RECORD+Constant.BREAK;
								}else{
									LOGS_ERROR += RECORD_VALUE+Constant.QUOTE+record[7]+Constant.QUOTE+" is NOT VALID GROUP - FAILED | LINE NUMBER: "+LINE_RECORD+Constant.BREAK;
									isPassed = false;
									//break;
								}
							}else{
								LOGS_ERROR += RECORD_VALUE+Constant.QUOTE+record[6]+Constant.QUOTE+" is NOT VALID GENDER - FAILED | LINE NUMBER: "+LINE_RECORD+Constant.BREAK;
								isPassed = false;
								//break;
							}
						}else{
							LOGS_ERROR += RECORD_VALUE+Constant.QUOTE+record[5]+Constant.QUOTE+" is NOT VALID DATE - FAILED | LINE NUMBER: "+LINE_RECORD+Constant.BREAK;
							isPassed = false;
							//break;
						}
					}else{
						LOGS_ERROR += "-->Error! No value on REQUIRED FIELDS | "+ " LINE NUMBER: "+LINE_RECORD+Constant.BREAK;
						isPassed = false;
						//break;
					}
				}else{
					LOGS_ERROR += "-->Error! No value on REQUIRED FIELDS | "+ " LINE NUMBER: "+LINE_RECORD+Constant.BREAK;
					isPassed = false;
				}
			  }
		
			}// --end of while
			
			
		

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("DONE IN VALIDATING CSV FILE");
		int TOTAL = LINE_RECORD-1;
		System.out.println("Total of records: "+TOTAL);
		LOGS_SUMMARY +="DONE IN VALIDATING CSV FILE"+Constant.BREAK;
		LOGS_SUMMARY +="Total of all records: "+TOTAL+Constant.BREAK;
		
		modelMap.addAttribute("LOGS_INFORMATION",LOGS_INFORMATION);
		modelMap.addAttribute("LOGS_ERROR",LOGS_ERROR);
		modelMap.addAttribute("LOGS_SUMMARY",LOGS_SUMMARY);
		
		return isPassed;
  }
  
  
  
  
  
  
}