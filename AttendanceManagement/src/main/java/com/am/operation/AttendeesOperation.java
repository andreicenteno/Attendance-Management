package com.am.operation;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.bean.AttendeesBean;
import com.am.bean.MinistryBean;
import com.am.common.BaseResponse;
import com.am.common.BeanMapper;
import com.am.model.Attendees;
import com.am.model.Ministry;
import com.am.service.AttendeesService;
import com.am.service.GroupService;
import com.am.service.MinistryService;
import com.am.service.ServiceService;


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
	
	
}