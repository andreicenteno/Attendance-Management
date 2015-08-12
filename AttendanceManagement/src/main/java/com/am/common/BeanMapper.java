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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.am.bean.AttendeesBean;
import com.am.bean.AttendeesSummaryViewBean;
import com.am.bean.AttendeesViewBean;
import com.am.bean.FirstTimerBean;
import com.am.bean.GroupBean;
import com.am.bean.MinistryBean;
import com.am.bean.ServiceAttendanceViewBean;
import com.am.bean.ServiceBean;
import com.am.bean.SundayServiceAttendeesBean;
import com.am.bean.SundayServiceBean;
import com.am.model.Attendees;
import com.am.model.AttendeesSummaryView;
import com.am.model.AttendeesView;
import com.am.model.FirstTimer;
import com.am.model.Group;
import com.am.model.Ministry;
import com.am.model.ServiceAttendanceView;
import com.am.model.ServiceEntity;
import com.am.model.SundayService;
import com.am.model.SundayServiceAttendees;
import com.am.service.AttendeesService;
import com.am.utils.ConvertionUtil;

public class BeanMapper extends EntityMapper{
	
	@Autowired
	private AttendeesService attendeesService;
	

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
				
				GroupBean groupBean = new GroupBean();
				groupBean.setGroupId(entity.getId());
				groupBean.setGroupName(entity.getGroup().getGroupName());
				bean.setGroupBean(groupBean);
				
				MinistryBean ministryBean = new MinistryBean();
				ministryBean.setMinistryId(entity.getMinistry().getId());
				ministryBean.setMinistryName(entity.getMinistry().getMinistryName());
				bean.setMinistryBean(ministryBean);
				
				
				
				
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
		
		GroupBean groupBean = new GroupBean();
		groupBean.setGroupId(entity.getGroup().getId());
		bean.setGroupBean(groupBean);
		
		MinistryBean ministryBean = new MinistryBean();
		ministryBean.setMinistryId(entity.getMinistry().getId());
		bean.setMinistryBean(ministryBean);
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
	
	
	public List<GroupBean> prepareListOfGroup(List<Group> groups) {
		List<GroupBean> beans = null;
		if (groups != null && !groups.isEmpty()) {
			beans = new ArrayList<GroupBean>();
			GroupBean bean = null;
			for (Group group : groups) {
				bean = new GroupBean();
				bean.setGroupId(group.getId());
				bean.setGroupName(group.getGroupName());
				bean.setDescription(group.getDescription());
				bean.setCreateTime(group.getCreateTime());
				bean.setUpdateTime(group.getUpdateTime());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public GroupBean prepareGroupBean(Group group){
		GroupBean bean = new GroupBean();
		bean.setGroupId(group.getId());
		bean.setGroupName(group.getGroupName());
		bean.setDescription(group.getDescription());
		bean.setCreateTime(group.getCreateTime());
		bean.setUpdateTime(group.getUpdateTime());
		return bean;
	}
	
	
	public List<ServiceAttendanceViewBean> prepareListOfServiceAttendanceView(List<ServiceAttendanceView> serviceAttendanceViews) {
		List<ServiceAttendanceViewBean> beans = null;
		if (serviceAttendanceViews != null && !serviceAttendanceViews.isEmpty()) {
			beans = new ArrayList<ServiceAttendanceViewBean>();
			ServiceAttendanceViewBean bean = null;
			for (ServiceAttendanceView entity : serviceAttendanceViews) {
				bean = new ServiceAttendanceViewBean();
				bean.setSundayServiceId(entity.getSundayServiceId());
				bean.setTotal(entity.getTotal());
				bean.setTotalOfKkb(entity.getTotalOfKkb());
				bean.setTotalOfYam(entity.getTotalOfYam());
				bean.setTotalOfMen(entity.getTotalOfMen());
				bean.setTotalOfWomen(entity.getTotalOfWomen());
				bean.setTotalOfChildren(entity.getTotalOfChildren());
				bean.setTotalOfKkbMale(entity.getTotalOfKkbMale());
				bean.setTotalOfKkbFemale(entity.getTotalOfKkbFemale());
				bean.setTotalOfYamMale(entity.getTotalOfYamMale());
				bean.setTotalOfYamFemale(entity.getTotalOfYamFemale());
				bean.setTotalOfChildrenMale(entity.getTotalOfChildrenMale());
				bean.setTotalOfChildrenFemale(entity.getTotalOfChildrenFemale());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public List<AttendeesViewBean> prepareListOfAttendeesView(List<AttendeesView> attendeesViews) {
		List<AttendeesViewBean> beans = null;
		if (attendeesViews != null && !attendeesViews.isEmpty()) {
			beans = new ArrayList<AttendeesViewBean>();
			AttendeesViewBean bean = null;
			for (AttendeesView entity : attendeesViews) {
				bean = new AttendeesViewBean();
				bean.setAttendeesId(entity.getAttendeesId());
				bean.setFirstName(entity.getFirstName());
				bean.setLastName(entity.getLastName());
				bean.setMiddleName(entity.getMiddleName());
				bean.setAddress(entity.getAddress());
				bean.setContactNumber(entity.getContactNumber());
				bean.setBirthday(entity.getBirthday().toString());
				bean.setGender(entity.getGender());
				bean.setIsMember(entity.getIsMember());
				bean.setGroupId(entity.getGroupId());
				bean.setGroupName(entity.getGroupName());
				bean.setMinistryId(entity.getMinistryId());
				bean.setMinistryName(entity.getMinistryName());
				bean.setYearOld(entity.getYearOld());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public List<AttendeesSummaryViewBean> prepareListOfAttendeesSummaryView(List<AttendeesSummaryView> attendeesSummaryViews) {
		List<AttendeesSummaryViewBean> beans = null;
		if (attendeesSummaryViews != null && !attendeesSummaryViews.isEmpty()) {
			beans = new ArrayList<AttendeesSummaryViewBean>();
			AttendeesSummaryViewBean bean = null;
			for (AttendeesSummaryView entity : attendeesSummaryViews) {
				bean = new AttendeesSummaryViewBean();
				bean.setTotal(entity.getTotal());
				bean.setTotalOfKkb(entity.getTotalOfKkb());
				bean.setTotalOfYam(entity.getTotalOfYam());
				bean.setTotalOfMen(entity.getTotalOfMen());
				bean.setTotalOfWomen(entity.getTotalOfWomen());
				bean.setTotalOfChildren(entity.getTotalOfChildren());
				bean.setTotalOfKkbMale(entity.getTotalOfKkbMale());
				bean.setTotalOfKkbFemale(entity.getTotalOfKkbFemale());
				bean.setTotalOfYamMale(entity.getTotalOfYamMale());
				bean.setTotalOfYamFemale(entity.getTotalOfYamFemale());
				bean.setTotalOfChildrenMale(entity.getTotalOfChildrenMale());
				bean.setTotalOfChildrenFemale(entity.getTotalOfChildrenFemale());
				bean.setTotalOfMale(entity.getTotalOfMale());
				bean.setTotalOfFemale(entity.getTotalOfFemale());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public List<FirstTimerBean> prepareListOfFirstTimer(List<FirstTimer> firstTimers) {
		List<FirstTimerBean> beans = null;
		if (firstTimers != null && !firstTimers.isEmpty()) {
			beans = new ArrayList<FirstTimerBean>();
			FirstTimerBean bean = null;
			for (FirstTimer entity : firstTimers) {
				
				
				bean = new FirstTimerBean();
				bean.setFirst_timer_id(entity.getId());
				
				SundayServiceBean sundayServiceBean = new SundayServiceBean();
				sundayServiceBean.setSundayServiceId(entity.getSundayService().getId());
				sundayServiceBean.setServiceTitle(entity.getSundayService().getServiceTitle());
				bean.setSundayServiceBean(sundayServiceBean);
				
				if(entity.getAttendeesId() != 0){
					Attendees attendees = attendeesService.getAttendees(entity.getAttendeesId());
					if(attendees!= null){
						AttendeesBean attendeesBean = new AttendeesBean();
						attendeesBean.setAttendeesId(attendees.getId());
						attendeesBean.setFirstName(attendees.getFirstName());
						attendeesBean.setLastName(attendees.getLastName());
						bean.setAttendeesBean(attendeesBean);
					}
				}
				
				AttendeesBean guestBean = new AttendeesBean();
				guestBean.setAttendeesId(entity.getAttendeesGuest().getId());
				guestBean.setFirstName(entity.getAttendeesGuest().getFirstName());
				guestBean.setLastName(entity.getAttendeesGuest().getLastName());
				guestBean.setAddress(entity.getAttendeesGuest().getAddress());
				bean.setGuestBean(guestBean);
				
				bean.setRemarks(entity.getRemarks());
			
				bean.setCreateTime(entity.getCreateTime());
				bean.setUpdateTime(entity.getUpdateTime());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public FirstTimerBean prepareFirstTimerBean(FirstTimer firstTimer){
		FirstTimerBean bean = new FirstTimerBean();
		bean.setFirst_timer_id(firstTimer.getId());
		bean.setRemarks(firstTimer.getRemarks());
		
		SundayServiceBean sundayServiceBean = new SundayServiceBean();
		sundayServiceBean.setSundayServiceId(firstTimer.getSundayService().getId());
		sundayServiceBean.setServiceTitle(firstTimer.getSundayService().getServiceTitle());
		bean.setSundayServiceBean(sundayServiceBean);
		
		
		Attendees attendees = attendeesService.getAttendees(firstTimer.getAttendeesId());
 		if(attendees!=null){
			AttendeesBean attendeesBean = new AttendeesBean();
			attendeesBean.setAttendeesId(firstTimer.getAttendeesId());
			attendeesBean.setFirstName(attendees.getFirstName());
			attendeesBean.setLastName(attendees.getLastName());
			bean.setAttendeesBean(attendeesBean);
 		}
		
		AttendeesBean guestBean = new AttendeesBean();
		guestBean.setAttendeesId(firstTimer.getAttendeesGuest().getId());
		guestBean.setFirstName(firstTimer.getAttendeesGuest().getFirstName());
		guestBean.setLastName(firstTimer.getAttendeesGuest().getLastName());
		bean.setGuestBean(guestBean);
		
		return bean;
	}
	
	
	
}
	