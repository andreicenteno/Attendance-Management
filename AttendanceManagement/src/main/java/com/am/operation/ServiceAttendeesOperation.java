package com.am.operation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.bean.SundayServiceAttendeesBean;
import com.am.common.BaseResponse;
import com.am.common.BeanMapper;
import com.am.service.SundayServiceAttendeesService;



@Service("serviceAttendeesOperation")
public class ServiceAttendeesOperation extends BeanMapper{
	private static Logger LOGGER = Logger.getLogger(ServiceAttendeesOperation.class);

	@Autowired
	private SundayServiceAttendeesService sundayServiceAttendeesService;
	
	
	public BaseResponse insertSundayServiceAttendees(SundayServiceAttendeesBean sundayServiceAttendeesBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			sundayServiceAttendeesService.insert(prepareSundayServiceAttendeesModel(sundayServiceAttendeesBean));
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("insertSundayServiceAttendees:" + baseResponse.toString());
		System.out.println("insertSundayServiceAttendees: " +baseResponse.toString());
		return baseResponse;
	}
	
	public BaseResponse deleteSundayServiceAttendees(SundayServiceAttendeesBean sundayServiceAttendeesBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			sundayServiceAttendeesService.delete(prepareSundayServiceAttendeesModel(sundayServiceAttendeesBean));
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("deleteSundayServiceAttendees:" + baseResponse.toString());
		System.out.println("deleteSundayServiceAttendees: " +baseResponse.toString());
		return baseResponse;
	}
	
	
	
}