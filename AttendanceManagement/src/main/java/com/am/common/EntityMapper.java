package com.am.common;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import com.am.bean.AttendeesBean;
import com.am.bean.FirstTimerBean;
import com.am.bean.GroupBean;
import com.am.bean.MinistryBean;
import com.am.bean.ServiceBean;
import com.am.bean.SundayServiceAttendeesBean;
import com.am.bean.SundayServiceBean;
import com.am.model.Attendees;
import com.am.model.FirstTimer;
import com.am.model.Group;
import com.am.model.Ministry;
import com.am.model.ServiceEntity;
import com.am.model.SundayService;
import com.am.model.SundayServiceAttendees;
import com.am.service.AttendeesService;
import com.am.service.FirstTimerService;
import com.am.utils.ConvertionUtil;



public class EntityMapper {
	
	@Autowired
	private FirstTimerService firstTimerService;
	
	@Autowired
	private AttendeesService attendeesService;
	
	
	public Ministry prepareMinistryModel(MinistryBean ministryBean){
		Ministry ministry = new Ministry();
		ministry.setId(ministryBean.getMinistryId());
		ministry.setMinistryName(ministryBean.getMinistryName());
		ministry.setDescription(ministryBean.getDescription());
		ministry.setCreateTime(ministryBean.getCreateTime());
		ministry.setUpdateTime(ministryBean.getUpdateTime());
		return ministry;
	}
	
	public ServiceEntity prepareServiceModel(ServiceBean serviceBean){
		ServiceEntity entity = new ServiceEntity();
		entity.setId(serviceBean.getServiceId());
		entity.setServiceName(serviceBean.getServiceName());
		entity.setDescription(serviceBean.getDescription());
		entity.setCreateTime(serviceBean.getCreateTime());
		entity.setUpdateTime(serviceBean.getUpdateTime());
		return entity;
	}
	
	
	public Attendees prepareAttendeesModel(AttendeesBean attendeesBean) throws ParseException{
		
		Attendees entity = new Attendees();
		entity.setId(attendeesBean.getAttendeesId());
		entity.setFirstName(attendeesBean.getFirstName());
		entity.setLastName(attendeesBean.getLastName());
		entity.setMiddleName(attendeesBean.getMiddleName());
		entity.setAddress(attendeesBean.getAddress());
		entity.setContactNumber(attendeesBean.getContactNumber());
		entity.setBirthday(ConvertionUtil.convertFormatter1().parse(attendeesBean.getBirthday()));
		entity.setGender(attendeesBean.getGender());
		entity.setIsFirstTimer(attendeesBean.getIsFirstTimer());
		entity.setFirstTimerDate(attendeesBean.getFirstTimerDate());
		entity.setIsMember(attendeesBean.getIsMember());
		entity.setMemberDate(attendeesBean.getMemberDate());
		Group group = new Group();
		group.setId(attendeesBean.getGroupBean().getGroupId());
		entity.setGroup(group);
		
		Ministry ministry = new Ministry();
		ministry.setId(attendeesBean.getMinistryBean().getMinistryId());
		entity.setMinistry(ministry);
		
		
		return entity;
	}
	
	public SundayService prepareSundayServiceModel(SundayServiceBean sundayServiceBean){
		SundayService entity = new SundayService();
		entity.setId(sundayServiceBean.getSundayServiceId());
		entity.setServiceTitle(sundayServiceBean.getServiceTitle());
		entity.setDescription(sundayServiceBean.getDescription());
		ServiceEntity serviceEntity = new ServiceEntity();
		serviceEntity.setId(sundayServiceBean.getServiceBean().getServiceId());
		entity.setServiceEntity(serviceEntity);
		
		return entity;
	}
	
	public SundayServiceAttendees prepareSundayServiceAttendeesModel(SundayServiceAttendeesBean sundayServiceAttendeesBean){
		SundayServiceAttendees entity = new SundayServiceAttendees();
		entity.setId(sundayServiceAttendeesBean.getSundayServiceAttendeesId());
		Attendees attendees = new Attendees();
		attendees.setId(sundayServiceAttendeesBean.getAttendeesBean().getAttendeesId());
		entity.setAttendees(attendees);
		
		SundayService sundayService = new SundayService();
		sundayService.setId(sundayServiceAttendeesBean.getSundayServiceBean().getSundayServiceId());
		entity.setSundayService(sundayService);
		
		entity.setCreateTime(sundayServiceAttendeesBean.getCreateTime());
		entity.setUpdateTime(sundayServiceAttendeesBean.getUpdateTime());
		
		return entity;
	}
	

	public Group prepareGroupModel(GroupBean groupBean){
		Group group = new Group();
		group.setId(groupBean.getGroupId());
		group.setGroupName(groupBean.getGroupName());
		group.setDescription(groupBean.getDescription());
		group.setCreateTime(groupBean.getCreateTime());
		group.setUpdateTime(groupBean.getUpdateTime());
		return group;
	}
	
	
	public BaseResponse prepareAttendeesFirstTimerModel(FirstTimerBean firstTimerBean){
		BaseResponse baseResponse = new BaseResponse();
		try{
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.SUCCESSFUL);
			
			Attendees attendeesGuest = new Attendees();
			attendeesGuest.setFirstName(firstTimerBean.getGuestBean().getFirstName());
			attendeesGuest.setLastName(firstTimerBean.getGuestBean().getLastName());
			attendeesGuest.setMiddleName(firstTimerBean.getGuestBean().getMiddleName());
			attendeesGuest.setAddress(firstTimerBean.getGuestBean().getAddress());
			attendeesGuest.setContactNumber(firstTimerBean.getGuestBean().getContactNumber());
			attendeesGuest.setGender(firstTimerBean.getGuestBean().getGender());
			attendeesGuest.setBirthday(ConvertionUtil.convertFormatter1().parse(firstTimerBean.getGuestBean().getBirthday()));
			Group group = new Group();
			group.setId(firstTimerBean.getGuestBean().getGroupBean().getGroupId());
			attendeesGuest.setGroup(group);
			
			Ministry ministry = new Ministry();
			ministry.setId(firstTimerBean.getGuestBean().getMinistryBean().getMinistryId());
			attendeesGuest.setMinistry(ministry);
			
			attendeesService.insert(attendeesGuest);
			
			FirstTimer firstTimer = new FirstTimer();
			firstTimer.setRemarks(firstTimerBean.getRemarks());
			SundayService sundayService = new SundayService();
			sundayService.setId(firstTimerBean.getSundayServiceBean().getSundayServiceId());
			firstTimer.setAttendeesGuest(attendeesGuest);
			firstTimer.setSundayService(sundayService);
			if(firstTimerBean.getRemarks().equals("Invited"))
				firstTimer.setAttendeesId(firstTimerBean.getAttendeesBean().getAttendeesId());
			
			firstTimerService.insert(firstTimer);
		}catch(Exception e){
			com.am.common.ResponseCodeUtil.setResponseCode(baseResponse, com.am.common.ResponseCode.TECHNICAL_ERROR);
		}
		
		return baseResponse;
		
	}
	

}