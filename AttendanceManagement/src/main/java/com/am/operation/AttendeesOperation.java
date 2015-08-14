package com.am.operation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.am.bean.AttendeesBean;
import com.am.bean.GroupBean;
import com.am.bean.MinistryBean;
import com.am.common.BaseResponse;
import com.am.common.BeanMapper;
import com.am.common.Constant;
import com.am.model.Attendees;
import com.am.service.AttendeesService;


@Service("attendeesOperation")
public class AttendeesOperation extends BeanMapper{
	private static Logger LOGGER = Logger.getLogger(AttendeesOperation.class);

	@Autowired
	private AttendeesService attendeesService;
	
	
	
	public BaseResponse insertAttendees(AttendeesBean attendeesBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(StringUtils.isNotEmpty(attendeesBean.getFirstName()) && StringUtils.isNotEmpty(attendeesBean.getLastName())
					&& StringUtils.isNotEmpty(attendeesBean.getAddress()) && StringUtils.isNotEmpty(attendeesBean.getContactNumber())
					&& StringUtils.isNotEmpty(attendeesBean.getGender().toString()) && StringUtils.isNotEmpty(attendeesBean.getBirthday())){
				Attendees attendees = prepareAttendeesModel(attendeesBean);
				attendeesService.insert(attendees);
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);
			}
			
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("insertAttendees:" + baseResponse.toString());
		System.out.println("insertAttendees: " +baseResponse.toString());
		return baseResponse;
	}
	
	
	public BaseResponse updateAttendees(AttendeesBean attendeesBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(StringUtils.isNotEmpty(attendeesBean.getFirstName()) && StringUtils.isNotEmpty(attendeesBean.getLastName())
					&& StringUtils.isNotEmpty(attendeesBean.getAddress()) && StringUtils.isNotEmpty(attendeesBean.getContactNumber())
					&& StringUtils.isNotEmpty(attendeesBean.getGender().toString()) && StringUtils.isNotEmpty(attendeesBean.getBirthday())){
				Attendees entity = prepareAttendeesModel(attendeesBean);
				attendeesService.update(entity);
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);
			}
			
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
		LOGGER.info("updateAttendees:" + baseResponse.toString());
		System.out.println("updateAttendees: " +baseResponse.toString());
		return baseResponse;
	}
	
	public BaseResponse deleteAttendees(AttendeesBean attendeesBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			System.out.println(attendeesBean.getAttendeesId());
			if(attendeesBean.getAttendeesId() != 0){
				Attendees entity = new Attendees();
				entity.setId(attendeesBean.getAttendeesId());
				attendeesService.delete(entity);
				
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);
			}
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
		LOGGER.info("deleteAttendees:" + baseResponse.toString());
		System.out.println("deleteAttendees: " +baseResponse.toString());
		return baseResponse;
	}
	
	
	 public BaseResponse insertCsvFileRecord(String filePath, ModelMap modelMap){
		   BaseResponse baseResponse = new BaseResponse();
		   com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			Boolean checkFormat = true;
			int LINE_RECORD = 0;
			String LOGS_INFORMATION = "";
			try{
				br = new BufferedReader(new FileReader(filePath));
				while ((line = br.readLine()) != null) {
					LINE_RECORD++;
					String[] record = line.split(cvsSplitBy);
					if(checkFormat)
						checkFormat = false;
					else{
						try{
							AttendeesBean attendeesBean = new AttendeesBean();
							attendeesBean.setFirstName(record[0]); // -- first_name
							attendeesBean.setLastName(record[1]); // last_name
							attendeesBean.setMiddleName(record[2]); // middle_name
							attendeesBean.setAddress(record[3]); // -- address	
							attendeesBean.setContactNumber(record[4]); // -- contact_number
							attendeesBean.setBirthday(record[5]); // birthday
							attendeesBean.setGender(Boolean.parseBoolean(record[6])); // gender
							GroupBean groupBean = new GroupBean();
							groupBean.setGroupId(Long.parseLong(record[7])); //-- group_id
							attendeesBean.setGroupBean(groupBean);
							MinistryBean ministryBean = new MinistryBean();
							ministryBean.setMinistryId(-1);
							attendeesBean.setMinistryBean(ministryBean);
							System.out.println(attendeesBean.toString()+attendeesBean.getGroupBean().getGroupId()+attendeesBean.getMinistryBean().getMinistryId());
							attendeesService.insert(prepareAttendeesModel(attendeesBean)); 
							LOGS_INFORMATION+="-->SUCCESSFULLY RECORD ADDED: "+attendeesBean.toString()+" | GroupId: "+attendeesBean.getGroupBean().getGroupId()+" | "+
											"MinistryId: "+attendeesBean.getMinistryBean().getMinistryId()+Constant.BREAK;
						}catch(Exception e){
							com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
							break;
						}
					}
				}
				int TOTAL = LINE_RECORD-1;
				LOGS_INFORMATION +=Constant.BREAK+"FINISH INSERT RECORDS. "+Constant.BREAK;
				LOGS_INFORMATION +="Total of all records: "+TOTAL+Constant.BREAK;
				String LOGS_SUMMARY = "TOTAL OF ALL RECORDS ADDED IN OUR DATABASE: "+TOTAL;
				modelMap.addAttribute("LOGS_SUMMARY",LOGS_SUMMARY);
				modelMap.addAttribute("LOGS_INFORMATION",LOGS_INFORMATION);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
			} catch (IOException e) {
				e.printStackTrace();
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			
		  return baseResponse;
	  }
	
	
}