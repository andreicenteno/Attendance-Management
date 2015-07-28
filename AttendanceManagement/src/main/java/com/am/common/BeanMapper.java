/**
 * 
 */
/**
 * @author User
 * 
 * Common Methods that can used in different class. or Constant variables.
 * 
 *
 */
package com.am.common;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.am.bean.AttendeesBean;
import com.am.bean.MinistryBean;
import com.am.bean.ServiceBean;
import com.am.bean.SundayServiceAttendeesBean;
import com.am.bean.SundayServiceBean;
import com.am.model.Attendees;
import com.am.model.Ministry;
import com.am.model.ServiceEntity;
import com.am.model.SundayService;
import com.am.model.SundayServiceAttendees;
import com.am.utils.ConvertionUtil;

public class BeanMapper extends EntityMapper{

   //Ministry
	public List<MinistryBean> prepareListOfMinistry(List<Ministry> ministries) {
		List<MinistryBean> beans = null;
		if (ministries != null && !ministries.isEmpty()) {
			beans = new ArrayList<MinistryBean>();
			MinistryBean bean = null;
			for (Ministry ministry : ministries) {
				bean = new MinistryBean();
				bean.setMinistryId(ministry.getId());
				bean.setMinistryName(ministry.getMinistryName());
				bean.setDescription(ministry.getDescription());
				bean.setCreateTime(ministry.getCreateTime());
				bean.setUpdateTime(ministry.getUpdateTime());
				beans.add(bean);
			}
		}
		return beans;

	}
	
	public MinistryBean prepareMinistryBean(Ministry ministry){
		MinistryBean bean = new MinistryBean();
		bean.setMinistryId(ministry.getId());
		bean.setMinistryName(ministry.getMinistryName());
		bean.setDescription(ministry.getDescription());
		bean.setCreateTime(ministry.getCreateTime());
		bean.setUpdateTime(ministry.getUpdateTime());
		return bean;
	}
	
	public List<ServiceBean> prepareListOfService(List<ServiceEntity> services) {
		List<ServiceBean> beans = null;
		if (services != null && !services.isEmpty()) {
			beans = new ArrayList<ServiceBean>();
			ServiceBean bean = null;
			for (ServiceEntity entity : services) {
				bean = new ServiceBean();
				bean.setServiceId(entity.getId());
				bean.setServiceName(entity.getServiceName());
				bean.setDescription(entity.getDescription());
				bean.setCreateTime(entity.getCreateTime());
				bean.setUpdateTime(entity.getUpdateTime());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public ServiceBean prepareServiceBean(ServiceEntity entity){
		ServiceBean bean = new ServiceBean();
		bean.setServiceId(entity.getId());
		bean.setServiceName(entity.getServiceName());
		bean.setDescription(entity.getDescription());
		bean.setCreateTime(entity.getCreateTime());
		bean.setUpdateTime(entity.getUpdateTime());
		return bean;
	}
	
	public List<AttendeesBean> prepareListOfAttendees(List<Attendees> attendees) throws ParseException {
		List<AttendeesBean> beans = null;
		if (attendees != null && !attendees.isEmpty()) {
			beans = new ArrayList<AttendeesBean>();
			AttendeesBean bean = null;
			for (Attendees entity : attendees) {
				bean = new AttendeesBean();
				bean.setAttendeesId(entity.getId());
				bean.setFirstName(entity.getFirstName());
				bean.setLastName(entity.getLastName());
				bean.setMiddleName(entity.getMiddleName());
				bean.setAddress(entity.getAddress());
				bean.setContactNumber(entity.getContactNumber());
				bean.setBirthday(ConvertionUtil.convertFormatter1().format(ConvertionUtil.formatDate(entity.getBirthday().toString())));
				bean.setGender(entity.getGender());
				bean.setIsFirstTimer(entity.getIsFirstTimer());
				bean.setFirstTimerDate(entity.getFirstTimerDate());
				bean.setIsMember(entity.getIsMember());
				bean.setMemberDate(entity.getMemberDate());
				bean.setCreateTime(entity.getCreateTime());
				bean.setUpdateTime(entity.getUpdateTime());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public AttendeesBean prepareAttendeesBean(Attendees entity) throws ParseException{
		AttendeesBean bean = new AttendeesBean();
		bean = new AttendeesBean();
		bean.setAttendeesId(entity.getId());
		bean.setFirstName(entity.getFirstName());
		bean.setLastName(entity.getLastName());
		bean.setMiddleName(entity.getMiddleName());
		bean.setAddress(entity.getAddress());
		bean.setContactNumber(entity.getContactNumber());
		bean.setBirthday(ConvertionUtil.convertFormatter1().format(ConvertionUtil.formatDate(entity.getBirthday().toString())));
		bean.setGender(entity.getGender());
		bean.setIsFirstTimer(entity.getIsFirstTimer());
		bean.setFirstTimerDate(entity.getFirstTimerDate());
		bean.setIsMember(entity.getIsMember());
		bean.setMemberDate(entity.getMemberDate());
		bean.setCreateTime(entity.getCreateTime());
		bean.setUpdateTime(entity.getUpdateTime());
		return bean;
	}
	
	
	public List<SundayServiceBean> prepareListOfSundayService(List<SundayService> sundayServices) {
		List<SundayServiceBean> beans = null;
		if (sundayServices != null && !sundayServices.isEmpty()) {
			beans = new ArrayList<SundayServiceBean>();
			SundayServiceBean bean = null;
			for (SundayService entity : sundayServices) {
				bean = new SundayServiceBean();
				bean.setSundayServiceId(entity.getId());
				bean.setServiceTitle(entity.getServiceTitle());
				bean.setDescription(entity.getDescription());
				ServiceBean serviceBean = new ServiceBean();
				serviceBean.setServiceId(entity.getServiceEntity().getId());
				serviceBean.setServiceName(entity.getServiceEntity().getServiceName());
				bean.setServiceBean(serviceBean);
				bean.setCreateTime(entity.getCreateTime().toString());
				bean.setUpdateTime(entity.getUpdateTime());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public SundayServiceBean prepareSundayServiceBean(SundayService entity){
		SundayServiceBean bean = new SundayServiceBean();
		bean.setSundayServiceId(entity.getId());
		bean.setServiceTitle(entity.getServiceTitle());
		bean.setDescription(entity.getDescription());
		ServiceBean serviceBean = new ServiceBean();
		serviceBean.setServiceId(entity.getServiceEntity().getId());
		serviceBean.setServiceName(entity.getServiceEntity().getServiceName());
		bean.setServiceBean(serviceBean);
		bean.setCreateTime(ConvertionUtil.convertFormatter3().format(ConvertionUtil.formatDate(entity.getCreateTime().toString())));
		bean.setUpdateTime(entity.getUpdateTime());
		return bean;
	}
	
	
	public List<SundayServiceAttendeesBean> prepareListOfSundayServiceAttendees(List<SundayServiceAttendees> sundayServiceAttendees) {
		List<SundayServiceAttendeesBean> beans = null;
		if (sundayServiceAttendees != null && !sundayServiceAttendees.isEmpty()) {
			beans = new ArrayList<SundayServiceAttendeesBean>();
			SundayServiceAttendeesBean bean = null;
			for (SundayServiceAttendees entity : sundayServiceAttendees) {
				bean = new SundayServiceAttendeesBean();
				bean.setSundayServiceAttendeesId(entity.getId());
				
				SundayServiceBean sundayServiceBean = new SundayServiceBean();
				sundayServiceBean.setSundayServiceId(entity.getSundayService().getId());
				bean.setSundayServiceBean(sundayServiceBean);
				
				AttendeesBean attendeesBean = new AttendeesBean();
				attendeesBean.setAttendeesId(entity.getAttendees().getId());
				attendeesBean.setFirstName(entity.getAttendees().getFirstName());
				attendeesBean.setLastName(entity.getAttendees().getLastName());
				attendeesBean.setMiddleName(entity.getAttendees().getMiddleName());
				attendeesBean.setContactNumber(entity.getAttendees().getContactNumber());
				attendeesBean.setGender(entity.getAttendees().getGender());
				attendeesBean.setAddress(entity.getAttendees().getAddress());
				bean.setAttendeesBean(attendeesBean);
				
				bean.setCreateTime(entity.getCreateTime());
				bean.setUpdateTime(entity.getUpdateTime());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public SundayServiceAttendeesBean prepareSundayServiceAttendeesBean(SundayServiceAttendees entity){
		SundayServiceAttendeesBean bean = new SundayServiceAttendeesBean();
		bean.setSundayServiceAttendeesId(entity.getId());
		SundayServiceBean sundayServiceBean = new SundayServiceBean();
		sundayServiceBean.setSundayServiceId(entity.getSundayService().getId());
		bean.setSundayServiceBean(sundayServiceBean);
		
		
		AttendeesBean attendeesBean = new AttendeesBean();
		attendeesBean.setAttendeesId(entity.getAttendees().getId());
		
		bean.setAttendeesBean(attendeesBean);
		
		
		bean.setCreateTime(entity.getCreateTime());
		bean.setUpdateTime(entity.getUpdateTime());
		return bean;
	}
	
	
}
	