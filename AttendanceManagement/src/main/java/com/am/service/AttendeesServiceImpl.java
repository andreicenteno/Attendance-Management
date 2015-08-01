package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.am.dao.AttendeesDao;
import com.am.model.Attendees;



@Service("attendeesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AttendeesServiceImpl implements AttendeesService{

	@Autowired
	private AttendeesDao attendeesDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insert(Attendees entity){
		attendeesDao.insert(entity);
	}
	
	public Attendees getAttendees(Long id){
		return attendeesDao.getAttendees(id);
	}
	
	public void delete(Attendees entity){
		attendeesDao.delete(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Attendees entity){
		attendeesDao.update(entity);
	}
	
	
	public List<Attendees> listAttendees(){
		return attendeesDao.listAttendees();
	}
	
	public List<Attendees> findAttendeesOnSundayService(long sunday_service_id){
		return attendeesDao.findAttendeesOnSundayService(sunday_service_id);
	}
	
	public List<Attendees> findAttendeesByName(String keyword){
		return attendeesDao.findAttendeesByName(keyword);
	}
	
	public List<Attendees> findAttendeesOnSundayServiceByName(long sunday_service_id, String keyword){
		return attendeesDao.findAttendeesOnSundayServiceByName(sunday_service_id, keyword);
	}
}