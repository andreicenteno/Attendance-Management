package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.am.dao.AttendeesViewDao;
import com.am.model.AttendeesView;



@Service("attendeesViewService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AttendeesViewServiceImpl implements AttendeesViewService{

	@Autowired
	private AttendeesViewDao  attendeesViewDao;
	
	public List<AttendeesView> listAttendeesSummaryView(){
		return attendeesViewDao.listAttendeesSummaryView();
	}
	
	public List<AttendeesView> listAttendeesViewByGroup(long group_id){
		return attendeesViewDao.listAttendeesViewByGroup(group_id);
	}
	
	
	public List<AttendeesView> listAttendeesViewwByGroupMinistry(long group_id, long ministry_id){
		return attendeesViewDao.listAttendeesViewwByGroupMinistry(group_id, ministry_id);
	}
	
	public List<AttendeesView> listAttendeesViewByGroupMinistryGender(long group_id, long ministry_id, Boolean gender){
		return attendeesViewDao.listAttendeesViewByGroupMinistryGender(group_id, ministry_id, gender);
	}
	
	public List<AttendeesView> listAttendeesViewByMinistry(long ministry_id){
		return attendeesViewDao.listAttendeesViewByMinistry(ministry_id);
	}
	
	public List<AttendeesView> listAttendeesViewByGender(Boolean gender){
		return attendeesViewDao.listAttendeesViewByGender(gender);
	}
	
	public List<AttendeesView> listAttendeesViewByGenderMinistry(Boolean gender, long ministry_id){
		return attendeesViewDao.listAttendeesViewByGenderMinistry(gender, ministry_id);
	}
	
	public List<AttendeesView> listAttendeesViewByGenderGroup(Boolean gender, long group_id){
		return attendeesViewDao.listAttendeesViewByGenderGroup(gender, group_id);
	}
}