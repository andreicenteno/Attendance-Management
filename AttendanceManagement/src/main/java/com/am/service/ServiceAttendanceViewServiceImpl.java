package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.am.dao.ServiceAttendanceViewDao;
import com.am.model.ServiceAttendanceView;


@Service("serviceAttendanceViewService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ServiceAttendanceViewServiceImpl implements ServiceAttendanceViewService{

	@Autowired
	private ServiceAttendanceViewDao serviceAttendanceViewDao;
	
	public List<ServiceAttendanceView> listServiceAttendanceView(long id){
		return serviceAttendanceViewDao.listServiceAttendanceView(id);
	}
}