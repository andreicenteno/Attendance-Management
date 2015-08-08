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
		Long id = (Long) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		entity.setId(id);
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
		String sql = "SELECT * FROM asystem.first_timers where sunday_service_id = ? order by create_time desc";
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