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
		String sql = "SELECT * FROM asystem.sunday_service_attendees order by update_time desc";
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
	
	@SuppressWarnings("unchecked")
	public List<SundayServiceAttendees> findSundayServiceAttendeesByName(String firstName, String lastName, String MiddleName, int iSearch, long sunday_service_id){
		// -- iSearch : 1 if 1 value | 2 if first_name and lastname | 3 if complete name
				String sql = null;
				if(iSearch <= 1){
					 sql = "SELECT * FROM asystem.sunday_service_attendees ssa"+  
								" LEFT JOIN asystem.attendees a ON a.attendees_id = ssa.attendees_id"+
								" WHERE ssa.sunday_service_id = ? AND (LOWER(a.first_name)like LOWER('%"+firstName+"%') OR LOWER(a.last_name)"+
								" like LOWER('%"+lastName+"%') OR LOWER(a.middle_name) like LOWER('%"+MiddleName+"%'))";
				}else if(iSearch == 2){
					sql = "SELECT * FROM asystem.sunday_service_attendees ssa"+  
							" LEFT JOIN asystem.attendees a ON a.attendees_id = ssa.attendees_id"+
							" WHERE ssa.sunday_service_id = ? AND (LOWER(a.first_name)like LOWER('%"+firstName+"%') AND LOWER(a.last_name)"+
							" like LOWER('%"+lastName+"%'))";
				}else if(iSearch >=3){
					 sql = "SELECT * FROM asystem.sunday_service_attendees ssa"+  
								" LEFT JOIN asystem.attendees a ON a.attendees_id = ssa.attendees_id"+
								" WHERE ssa.sunday_service_id = ? AND (LOWER(a.first_name)like LOWER('%"+firstName+"%') AND LOWER(a.last_name)"+
								" like LOWER('%"+lastName+"%') AND LOWER(a.middle_name) like LOWER('%"+MiddleName+"%'))";
				}
		   
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addEntity(SundayServiceAttendees.class);
		sqlQuery.setLong(0, sunday_service_id);
		return sqlQuery.list();
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<SundayServiceAttendees> findSundayServiceAttendeesByServiceIdGroupId(long sunday_service_id, long group_id){
		String sql = "SELECT * FROM asystem.sunday_service_attendees ssa"+
					" LEFT JOIN asystem.attendees a ON a.attendees_id = ssa.attendees_id"+
					" LEFT JOIN asystem.sunday_services ss ON ss.sunday_service_id = ssa.sunday_service_id"+
					" LEFT JOIN asystem.groups g ON g.group_id = a.group_id"+
					" LEFT JOIN asystem.ministries m ON m.ministry_id = a.ministry_id"+
					" WHERE ssa.sunday_service_id = ? AND g.group_id = ?"+
					" ORDER BY last_name";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(SundayServiceAttendees.class);
	    sqlQuery.setLong(0, sunday_service_id);
	    sqlQuery.setLong(1, group_id);
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