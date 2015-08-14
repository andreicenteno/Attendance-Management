package com.am.operation;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.am.bean.SundayServiceBean;
import com.am.common.BaseResponse;
import com.am.common.BeanMapper;
import com.am.model.SundayService;
import com.am.service.SundayServiceService;


@Service("sundayserviceOperation")
public class SundayServiceOperation extends BeanMapper{
	private static Logger LOGGER = Logger.getLogger(SundayServiceOperation.class);

	@Autowired
	private SundayServiceService sundayServiceService;
	
	
	public BaseResponse insertSundayService(SundayServiceBean sundayServiceBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(StringUtils.isNotEmpty(sundayServiceBean.getServiceTitle()) && StringUtils.isNotEmpty(sundayServiceBean.getDescription())){
				SundayService sundayService = prepareSundayServiceModel(sundayServiceBean);
				sundayServiceService.insert(sundayService);	
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);	
			}
			
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("insertSundayService:" + baseResponse.toString());
		System.out.println("insertSundayService: " +baseResponse.toString());
		return baseResponse;
	}
	
	
	public BaseResponse updateSundayService(SundayServiceBean sundayServiceBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(StringUtils.isNotEmpty(sundayServiceBean.getServiceTitle()) && StringUtils.isNotEmpty(sundayServiceBean.getDescription())){
				SundayService sundayService = prepareSundayServiceModel(sundayServiceBean);
				sundayServiceService.update(sundayService);	
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);	
			}
			
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("updateSundayService:" + baseResponse.toString());
		System.out.println("updateSundayService: " +baseResponse.toString());
		return baseResponse;
	}
	
	public BaseResponse deleteSundayService(SundayServiceBean sundayServiceBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			SundayService entity = new SundayService();
			entity.setId(sundayServiceBean.getSundayServiceId());
			sundayServiceService.delete(entity);
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("deleteSundayService:" + baseResponse.toString());
		System.out.println("deleteSundayService: " +baseResponse.toString());
		return baseResponse;
	}
	
}