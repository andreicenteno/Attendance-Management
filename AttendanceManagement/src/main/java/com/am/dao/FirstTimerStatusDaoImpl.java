package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.FirstTimerStatus;


@Repository("firstTimerStatusDao")
public class FirstTimerStatusDaoImpl implements FirstTimerStatusDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(FirstTimerStatus entity){
		Long id = (Long) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		entity.setId(id);
	}

	@SuppressWarnings("unchecked")
	public List<FirstTimerStatus> listFirstTimerStatus(){
		String sql = "SELECT * FROM asystem.first_timer_status order by first_timer_status_id asc";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(FirstTimerStatus.class);
	    return sqlQuery.list();
	}
	
	public FirstTimerStatus getFirstTimerStatus(Long id){
		return (FirstTimerStatus) sessionFactory.getCurrentSession().get(FirstTimerStatus.class, id);
	}
	
	public void delete(FirstTimerStatus entity){
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();

	}
	
	public void update(FirstTimerStatus entity){
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}
	
}