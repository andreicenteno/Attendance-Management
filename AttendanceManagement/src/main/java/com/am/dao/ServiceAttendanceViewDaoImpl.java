package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.ServiceAttendanceView;


@Repository("serviceAttendanceViewDao")
public class ServiceAttendanceViewDaoImpl implements ServiceAttendanceViewDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	public List<ServiceAttendanceView> listServiceAttendanceView(long id){
		String sql = "SELECT * FROM asystem.v_service_attendees where sunday_service_id ="+id;
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(ServiceAttendanceView.class);
	    return sqlQuery.list();
	}
	
	
	
}