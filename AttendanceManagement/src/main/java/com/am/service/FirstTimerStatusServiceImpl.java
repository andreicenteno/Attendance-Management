package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.am.dao.FirstTimerStatusDao;
import com.am.model.FirstTimerStatus;


@Service("firstTimerStatusService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FirstTimerStatusServiceImpl implements FirstTimerStatusService{

	@Autowired
	private FirstTimerStatusDao firstTimerStatusDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insert(FirstTimerStatus entity){
		firstTimerStatusDao.insert(entity);
	}
	
	public FirstTimerStatus getFirstTimerStatus(Long id){
		return firstTimerStatusDao.getFirstTimerStatus(id);
	}
	
	public void delete(FirstTimerStatus entity){
		firstTimerStatusDao.delete(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(FirstTimerStatus entity){
		firstTimerStatusDao.update(entity);
	}
	
	
	public List<FirstTimerStatus> listFirstTimerStatus(){
		return firstTimerStatusDao.listFirstTimerStatus();
	}
}