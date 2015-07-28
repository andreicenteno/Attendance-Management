package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.Ministry;


@Repository("ministryDao")
public class MinistryDaoImpl implements MinistryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Ministry entity){
		Long id = (Long) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		entity.setId(id);
	}

	@SuppressWarnings("unchecked")
	public List<Ministry> listMinistry(){
		String sql = "SELECT * FROM asystem.ministries order by update_time desc";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(Ministry.class);
	    return sqlQuery.list();
	}
	
	public Ministry getMinistry(Long id){
		return (Ministry) sessionFactory.getCurrentSession().get(Ministry.class, id);
	}
	
	public void delete(Ministry entity){
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();

	}
	
	public void update(Ministry entity){
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}
	
}