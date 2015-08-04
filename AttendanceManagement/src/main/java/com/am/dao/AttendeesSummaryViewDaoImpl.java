package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.AttendeesSummaryView;


@Repository("attendeesSummaryViewDao")
public class AttendeesSummaryViewDaoImpl implements AttendeesSummaryViewDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	public List<AttendeesSummaryView> listAttendeesSummaryView(){
		String sql = "SELECT * FROM asystem.v_attendees_summary";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(AttendeesSummaryView.class);
	    return sqlQuery.list();
	}
	
	
	
	
	
	
}