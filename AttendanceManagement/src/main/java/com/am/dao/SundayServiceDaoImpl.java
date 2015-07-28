package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.SundayService;


@Repository("sundayserviceDao")
public class SundayServiceDaoImpl implements SundayServiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(SundayService entity){
		Long id = (Long) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		entity.setId(id);
	}

	@SuppressWarnings("unchecked")
	public List<SundayService> listSundayService(){
		String sql = "SELECT * FROM asystem.sunday_services";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(SundayService.class);
	    return sqlQuery.list();
	}
	
	public SundayService getSundayService(Long id){
		return (SundayService) sessionFactory.getCurrentSession().get(SundayService.class, id);
	}
	
	public void delete(SundayService entity){
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();

	}
	
	public void update(SundayService entity){
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}
	
}