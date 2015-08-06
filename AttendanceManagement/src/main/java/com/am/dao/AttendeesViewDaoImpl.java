package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.AttendeesView;


@Repository("attendeesViewDao")
public class AttendeesViewDaoImpl implements AttendeesViewDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	public List<AttendeesView> listAttendeesSummaryView(){
		String sql = "SELECT * FROM asystem.v_attendees order by last_name";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(AttendeesView.class);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AttendeesView> listAttendeesViewByGroup(long group_id){
		String sql = "SELECT * FROM asystem.v_attendees where group_id = "+group_id;
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(AttendeesView.class);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AttendeesView> listAttendeesViewwByGroupMinistry(long group_id, long ministry_id){
		String sql = "SELECT * FROM asystem.v_attendees where group_id = ? AND ministry_id = ?";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(AttendeesView.class);
	    sqlQuery.setLong(0, group_id);
	    sqlQuery.setLong(1, ministry_id);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AttendeesView> listAttendeesViewByGroupMinistryGender(long group_id, long ministry_id, Boolean gender){
		String sql = "SELECT * FROM asystem.v_attendees where group_id = ? AND ministry_id = ? AND gender = ?";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(AttendeesView.class);
	    sqlQuery.setLong(0, group_id);
	    sqlQuery.setLong(1, ministry_id);
	    sqlQuery.setBoolean(2, gender);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AttendeesView> listAttendeesViewByMinistry(long ministry_id){
		String sql = "SELECT * FROM asystem.v_attendees where ministry_id = "+ministry_id;
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(AttendeesView.class);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AttendeesView> listAttendeesViewByGender(Boolean gender){
		String sql = "SELECT * FROM asystem.v_attendees where gender = "+gender;
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(AttendeesView.class);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AttendeesView> listAttendeesViewByGenderMinistry(Boolean gender, long ministry_id){
		String sql = "SELECT * FROM asystem.v_attendees where gender = ? AND ministry_id = ?";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(AttendeesView.class);
	    sqlQuery.setBoolean(0, gender);
	    sqlQuery.setLong(1, ministry_id);
	    return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AttendeesView> listAttendeesViewByGenderGroup(Boolean gender, long group_id){
		String sql = "SELECT * FROM asystem.v_attendees where gender = ? AND group_id = ?";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(AttendeesView.class);
	    sqlQuery.setBoolean(0, gender);
	    sqlQuery.setLong(1, group_id);
	    return sqlQuery.list();
	}
			
	
	
	
	
}