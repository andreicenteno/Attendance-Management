package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.am.dao.ServiceDao;
import com.am.model.ServiceEntity;


@Service("serviceEntityService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ServiceServiceImpl implements ServiceService{

	@Autowired
	private ServiceDao serviceDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insert(ServiceEntity entity){
		serviceDao.insert(entity);
	}
	
	public ServiceEntity getService(Long id){
		return serviceDao.getService(id);
	}
	
	public void delete(ServiceEntity entity){
		serviceDao.delete(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(ServiceEntity entity){
		serviceDao.update(entity);
	}
	
	
	public List<ServiceEntity> listService(){
		return serviceDao.listService();
	}
}