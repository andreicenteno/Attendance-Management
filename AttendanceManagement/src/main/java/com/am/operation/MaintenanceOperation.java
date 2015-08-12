package com.am.operation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.bean.GroupBean;
import com.am.bean.MinistryBean;
import com.am.bean.ServiceBean;
import com.am.common.BaseResponse;
import com.am.common.BeanMapper;
import com.am.model.Group;
import com.am.model.Ministry;
import com.am.model.ServiceEntity;
import com.am.service.GroupService;
import com.am.service.MinistryService;
import com.am.service.ServiceService;


@Service("maintenanceOperation")
public class MaintenanceOperation extends BeanMapper{
	private static Logger LOGGER = Logger.getLogger(MaintenanceOperation.class);
	
	@Autowired
	private MinistryService ministryService;
	
	@Autowired 
	private GroupService groupService;
	
	@Autowired
	private ServiceService serviceService;
	
	
	
	public BaseResponse updateMinistry(MinistryBean ministryBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(org.apache.commons.lang.StringUtils.isNotEmpty(ministryBean.getMinistryName()) && org.apache.commons.lang.StringUtils.isNotEmpty(ministryBean.getDescription())){
				Ministry ministry = prepareMinistryModel(ministryBean);
				ministryService.update(ministry);
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);
			}
			
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("updateMinistry:" + baseResponse.toString());
		System.out.println("updateMinistry: " +baseResponse.toString());
		return baseResponse;
	}
	
	
	public BaseResponse insertMinistry(MinistryBean ministryBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(org.apache.commons.lang.StringUtils.isNotEmpty(ministryBean.getMinistryName()) && org.apache.commons.lang.StringUtils.isNotEmpty(ministryBean.getDescription())){
				Ministry ministry = prepareMinistryModel(ministryBean);
				ministryService.insert(ministry);
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);
			}
			
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("insertMinistry:" + baseResponse.toString());
		System.out.println("insertMinistry: " +baseResponse.toString());
		return baseResponse;
	}
	
	public BaseResponse deleteMinistry(MinistryBean ministryBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			Ministry ministry = prepareMinistryModel(ministryBean);
			ministryService.delete(ministry);
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("deleteMinistry:" + baseResponse.toString());
		System.out.println("deleteMinistry: " +baseResponse.toString());
		return baseResponse;
	}
	
	
	public BaseResponse updateGroup(GroupBean groupBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(org.apache.commons.lang.StringUtils.isNotEmpty(groupBean.getGroupName()) && org.apache.commons.lang.StringUtils.isNotEmpty(groupBean.getDescription())){
				Group group= prepareGroupModel(groupBean);
				groupService.update(group);
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);
			}
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("updateGroup:" + baseResponse.toString());
		System.out.println("updateGroup: " +baseResponse.toString());
		return baseResponse;
	}
	
	public BaseResponse insertGroup(GroupBean groupBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(org.apache.commons.lang.StringUtils.isNotEmpty(groupBean.getGroupName()) && org.apache.commons.lang.StringUtils.isNotEmpty(groupBean.getDescription())){
				Group group= prepareGroupModel(groupBean);
				groupService.insert(group);
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);
			}
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("insertGroup:" + baseResponse.toString());
		System.out.println("insertGroup: " +baseResponse.toString());
		return baseResponse;
	}
	
	public BaseResponse deleteGroup(GroupBean groupBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			Group group = prepareGroupModel(groupBean);
			groupService.delete(group);
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("deleteGroup:" + baseResponse.toString());
		System.out.println("deleteGroup: " +baseResponse.toString());
		return baseResponse;
	}
	
	public BaseResponse insertService(ServiceBean serviceBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(org.apache.commons.lang.StringUtils.isNotEmpty(serviceBean.getServiceName()) && org.apache.commons.lang.StringUtils.isNotEmpty(serviceBean.getDescription())){
				ServiceEntity serviceEntity = prepareServiceModel(serviceBean);
				serviceService.insert(serviceEntity);
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);
			}
			
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("insertService:" + baseResponse.toString());
		System.out.println("insertService: " +baseResponse.toString());
		return baseResponse;
	}
	
	public BaseResponse updateService(ServiceBean serviceBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			if(org.apache.commons.lang.StringUtils.isNotEmpty(serviceBean.getServiceName()) && org.apache.commons.lang.StringUtils.isNotEmpty(serviceBean.getDescription())){
				ServiceEntity entity = prepareServiceModel(serviceBean);
				serviceService.update(entity);
			}else{
				com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.REQUIRED_FIELD);
			}
			
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("updateService:" + baseResponse.toString());
		System.out.println("updateService: " +baseResponse.toString());
		return baseResponse;
	}
	
	public BaseResponse deleteService(ServiceBean serviceBean){
		BaseResponse baseResponse = new BaseResponse();
		com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
		try{
			ServiceEntity entity = prepareServiceModel(serviceBean);
			serviceService.delete(entity);
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
			
		LOGGER.info("deleteService:" + baseResponse.toString());
		System.out.println("deleteService: " +baseResponse.toString());
		return baseResponse;
	}
	
	
	
}