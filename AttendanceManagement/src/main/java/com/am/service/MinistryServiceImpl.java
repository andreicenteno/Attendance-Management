package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.am.dao.MinistryDao;
import com.am.model.Ministry;


@Service("ministryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MinistryServiceImpl implements MinistryService{

	@Autowired
	private MinistryDao ministryDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insert(Ministry entity){
		ministryDao.insert(entity);
	}
	
	public Ministry getMinistry(Long id){
		return ministryDao.getMinistry(id);
	}
	
	public void delete(Ministry entity){
		ministryDao.delete(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Ministry entity){
		ministryDao.update(entity);
	}
	
	
	public List<Ministry> listMinistry(){
		return ministryDao.listMinistry();
	}
}