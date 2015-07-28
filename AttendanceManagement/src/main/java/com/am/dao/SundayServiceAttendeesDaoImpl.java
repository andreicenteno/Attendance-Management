package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.am.model.SundayServiceAttendees;


@Repository("sundayserviceAttendanceDao")
public class SundayServiceAttendeesDaoImpl implements SundayServiceAttendeesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(SundayServiceAttendees entity){
		Long id = (Long) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		entity.setId(id);
	}

	@SuppressWarnings("unchecked")
	public List<SundayServiceAttendees> listSundayServiceAttendees(){
		String sql = "SELECT * FROM asystem.sunday_service_attendees";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(SundayServiceAttendees.class);
	    return sqlQuery.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<SundayServiceAttendees> findSundayServiceAttendeesById(long sunday_service_id){
		String sql = "SELECT * FROM asystem.sunday_service_attendees where sunday_service_id = "+sunday_service_id;
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(SundayServiceAttendees.class);
	    return sqlQuery.list();
	}
	
	public SundayServiceAttendees getSundayServiceAttendees(Long id){
		return (SundayServiceAttendees) sessionFactory.getCurrentSession().get(SundayServiceAttendees.class, id);
	}
	
	public void delete(SundayServiceAttendees entity){
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();

	}
	
	public void update(SundayServiceAttendees entity){
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}
	
}