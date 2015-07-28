package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.Attendees;


@Repository("attendeesDao")
public class AttendeesDaoImpl implements AttendeesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Attendees entity){
		Long id = (Long) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		entity.setId(id);
	}

	@SuppressWarnings("unchecked")
	public List<Attendees> listAttendees(){
		String sql = "SELECT * FROM asystem.attendees";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(Attendees.class);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Attendees> findAttendeesOnSundayService(long sunday_service_id){
		String sql = "select * from asystem.attendees where attendees_id NOT IN (select attendees_id from asystem.sunday_service_attendees where sunday_service_id = "+sunday_service_id+")";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(Attendees.class);
	    return sqlQuery.list();
	}
	
	public Attendees getAttendees(Long id){
		return (Attendees) sessionFactory.getCurrentSession().get(Attendees.class, id);
	}
	
	public void delete(Attendees entity){
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();

	}
	
	public void update(Attendees entity){
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}
	
}