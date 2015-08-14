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
		String sql = "SELECT * FROM asystem.attendees order by update_time desc";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(Attendees.class);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Attendees> findAttendeesByName(String firstName, String lastName, String MiddleName, int iSearch){
		// -- iSearch : 1 if 1 value | 2 if first_name and lastname | 3 if complete name
		String sql = null;
		if(iSearch <= 1){
			sql = "select * from asystem.attendees where (LOWER(first_name)"+
					 " like LOWER('%"+firstName+"%') OR LOWER(last_name)"+
					 " like LOWER('%"+lastName+"%') OR LOWER(middle_name)"+
					 " like LOWER('%"+MiddleName+"%') )";
		}else if(iSearch == 2){
			sql = "select * from asystem.attendees where (LOWER(first_name)"+
					 " like LOWER('%"+firstName+"%') AND LOWER(last_name)"+
					 " like LOWER('%"+lastName+"%') )";
		}else if(iSearch >=3){
			sql = "select * from asystem.attendees where (LOWER(first_name)"+
					 " like LOWER('%"+firstName+"%') AND LOWER(last_name)"+
					 " like LOWER('%"+lastName+"%') AND LOWER(middle_name)"+
					 " like LOWER('%"+MiddleName+"%') )";
		}
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
	
	@SuppressWarnings("unchecked")
	public List<Attendees> findAttendeesOnSundayServiceByName(String firstName, String lastName, String MiddleName, int iSearch, long sunday_service_id){
		// -- iSearch : 1 if 1 value | 2 if first_name and lastname | 3 if complete name
				String sql = null;
		if(iSearch <= 1){
			sql = "select * from asystem.attendees where attendees_id NOT IN (select attendees_id from asystem.sunday_service_attendees where sunday_service_id = "+sunday_service_id+")"+
					 " AND (LOWER(first_name)"+
					 " like LOWER('%"+firstName+"%') OR LOWER(last_name)"+
					 " like LOWER('%"+lastName+"%') OR LOWER(middle_name)"+
					 " like LOWER('%"+MiddleName+"%') )";
		}else if(iSearch == 2){
			sql = "select * from asystem.attendees where attendees_id NOT IN (select attendees_id from asystem.sunday_service_attendees where sunday_service_id = "+sunday_service_id+")"+
					 " AND (LOWER(first_name)"+
					 " like LOWER('%"+firstName+"%') AND LOWER(last_name)"+
					 " like LOWER('%"+lastName+"%'))";
		}else if(iSearch >= 3){
			sql = "select * from asystem.attendees where attendees_id NOT IN (select attendees_id from asystem.sunday_service_attendees where sunday_service_id = "+sunday_service_id+")"+
					 " AND (LOWER(first_name)"+
					 " like LOWER('%"+firstName+"%') AND LOWER(last_name)"+
					 " like LOWER('%"+lastName+"%') AND LOWER(middle_name)"+
					 " like LOWER('%"+MiddleName+"%') )";
		}
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