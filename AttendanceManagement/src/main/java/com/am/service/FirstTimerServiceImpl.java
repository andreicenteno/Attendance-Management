package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.am.dao.FirstTimerDao;
import com.am.model.FirstTimer;


@Service("firstTimerService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FirstTimerServiceImpl implements FirstTimerService{

	@Autowired
	private FirstTimerDao firstTimerDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insert(FirstTimer entity){
		firstTimerDao.insert(entity);
	}
	
	public FirstTimer getFirstTimer(Long id){
		return firstTimerDao.getFirstTimer(id);
	}
	
	public void delete(FirstTimer entity){
		firstTimerDao.delete(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(FirstTimer entity){
		firstTimerDao.update(entity);
	}
	
	public List<FirstTimer> findFirstTimerBySundayServiceId(long sunday_service_id){
		return firstTimerDao.findFirstTimerBySundayServiceId(sunday_service_id);
	}
	
	public List<FirstTimer> listFirstTimer(){
		return firstTimerDao.listFirstTimer();
	}
	
	public List<FirstTimer> findFirstTimerByName(String firstName, String lastName, String MiddleName, int iSearch, long sunday_service_id){
		return firstTimerDao.findFirstTimerByName(firstName, lastName, MiddleName, iSearch, sunday_service_id);
	}
}