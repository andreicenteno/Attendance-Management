package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.am.dao.SundayServiceAttendeesDao;
import com.am.model.SundayServiceAttendees;

@Service("sundayserviceAttendanceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SundayServiceAttendeesServiceImpl implements SundayServiceAttendeesService{

	@Autowired
	private SundayServiceAttendeesDao sundayServiceAttendeesDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insert(SundayServiceAttendees entity){
		sundayServiceAttendeesDao.insert(entity);
	}
	
	public SundayServiceAttendees getSundayServiceAttendees(Long id){
		return sundayServiceAttendeesDao.getSundayServiceAttendees(id);
	}
	
	public void delete(SundayServiceAttendees entity){
		sundayServiceAttendeesDao.delete(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(SundayServiceAttendees entity){
		sundayServiceAttendeesDao.update(entity);
	}
	
	
	public List<SundayServiceAttendees> findSundayServiceAttendeesById(long sunday_service_id){
		return sundayServiceAttendeesDao.findSundayServiceAttendeesById(sunday_service_id);
	}
	
	public List<SundayServiceAttendees> listSundayServiceAttendees(){
		return sundayServiceAttendeesDao.listSundayServiceAttendees();
	}
	
	public List<SundayServiceAttendees> findSundayServiceAttendeesByServiceIdGroupId(long sunday_service_id, long group_id){
		return sundayServiceAttendeesDao.findSundayServiceAttendeesByServiceIdGroupId(sunday_service_id, group_id);
	}
}