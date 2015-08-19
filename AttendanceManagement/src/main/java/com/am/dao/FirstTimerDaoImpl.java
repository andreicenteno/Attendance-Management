package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.FirstTimer;


@Repository("firstTimerDao")
public class FirstTimerDaoImpl implements FirstTimerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(FirstTimer entity){
		String sql = "INSERT INTO asystem.first_timers (sunday_service_id, attendees_id, guest_id, remarks, first_timer_status_id) VALUES (?, ?, ?, ?, ?)";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	  sqlQuery.addEntity(FirstTimer.class);
	  sqlQuery.setLong(0, entity.getSundayService().getId());
	  sqlQuery.setLong(1, entity.getAttendeesId());
	  sqlQuery.setLong(2, entity.getAttendeesGuest().getId());
	  sqlQuery.setString(3, entity.getRemarks());
	  sqlQuery.setLong(4, entity.getFirstTimerStatus().getId());
	  sqlQuery.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<FirstTimer> listFirstTimer(){
		String sql = "SELECT * FROM asystem.first_timers order by update_time desc";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(FirstTimer.class);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FirstTimer> findFirstTimerBySundayServiceId(long sunday_service_id){
		String sql = "SELECT *, b.first_name||' '||b.last_name as fname_invite FROM asystem.first_timers f LEFT JOIN asystem.attendees a ON f.guest_id = a.attendees_id"+ 
			       " LEFT JOIN asystem.attendees b ON b.attendees_id = f.attendees_id WHERE f.sunday_service_id = ? order by f.create_time desc";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(FirstTimer.class);
	    sqlQuery.setLong(0, sunday_service_id);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FirstTimer> findFirstTimerByName(String firstName, String lastName, String MiddleName, int iSearch, long sunday_service_id){
		// -- iSearch : 1 if 1 value | 2 if first_name and lastname | 3 if complete name
				String sql = null;
				if(iSearch <= 1){
					sql = "SELECT *, b.first_name||' '||b.last_name as fname_invite FROM asystem.first_timers f LEFT JOIN asystem.attendees a ON f.guest_id = a.attendees_id"+
							" LEFT JOIN asystem.attendees b ON b.attendees_id = f.attendees_id WHERE f.sunday_service_id = ?"+ 
							" AND (LOWER(a.first_name)like LOWER('%"+firstName+"%') OR LOWER(a.last_name)"+
							" like LOWER('%"+lastName+"%') OR LOWER(a.middle_name) like LOWER('%"+MiddleName+"%')) order by f.create_time desc";
				}else if(iSearch == 2){
					sql = "SELECT *, b.first_name||' '||b.last_name as fname_invite FROM asystem.first_timers f LEFT JOIN asystem.attendees a ON f.guest_id = a.attendees_id"+
							" LEFT JOIN asystem.attendees b ON b.attendees_id = f.attendees_id WHERE f.sunday_service_id = ?"+ 
							" AND (LOWER(a.first_name)like LOWER('%"+firstName+"%') AND LOWER(a.last_name)"+
							" like LOWER('%"+lastName+"%')) order by f.create_time desc";
				}else if(iSearch >=3){
					sql = "SELECT *, b.first_name||' '||b.last_name as fname_invite FROM asystem.first_timers f LEFT JOIN asystem.attendees a ON f.guest_id = a.attendees_id"+
							" LEFT JOIN asystem.attendees b ON b.attendees_id = f.attendees_id WHERE f.sunday_service_id = ?"+ 
							" AND (LOWER(a.first_name)like LOWER('%"+firstName+"%') AND LOWER(a.last_name)"+
							" like LOWER('%"+lastName+"%') AND LOWER(a.middle_name) like LOWER('%"+MiddleName+"%')) order by f.create_time desc";
				}
				SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
				sqlQuery.addEntity(FirstTimer.class);
				sqlQuery.setLong(0, sunday_service_id);
				return sqlQuery.list();
	}
	
	
	public FirstTimer getFirstTimer(Long id){
		return (FirstTimer) sessionFactory.getCurrentSession().get(FirstTimer.class, id);
	}
	
	public void delete(FirstTimer entity){
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();

	}
	
	public void update(FirstTimer entity){
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}
	
}