package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.am.dao.SundayServiceDao;
import com.am.model.SundayService;

@Service("sundayserviceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SundayServiceServiceImpl implements SundayServiceService{

	@Autowired
	private SundayServiceDao sundayServiceDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insert(SundayService entity){
		sundayServiceDao.insert(entity);
	}
	
	public SundayService getSundayService(Long id){
		return sundayServiceDao.getSundayService(id);
	}
	
	public void delete(SundayService entity){
		sundayServiceDao.delete(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(SundayService entity){
		sundayServiceDao.update(entity);
	}
	
	
	public List<SundayService> listSundayService(){
		return sundayServiceDao.listSundayService();
	}
}