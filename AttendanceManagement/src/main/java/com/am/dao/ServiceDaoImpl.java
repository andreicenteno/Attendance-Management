package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.ServiceEntity;


@Repository("serviceEntityDao")
public class ServiceDaoImpl implements ServiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(ServiceEntity entity){
		Long id = (Long) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		entity.setId(id);
	}

	@SuppressWarnings("unchecked")
	public List<ServiceEntity> listService(){
		String sql = "SELECT * FROM asystem.services";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(ServiceEntity.class);
	    return sqlQuery.list();
	}
	
	public ServiceEntity getService(Long id){
		return (ServiceEntity) sessionFactory.getCurrentSession().get(ServiceEntity.class, id);
	}
	
	public void delete(ServiceEntity entity){
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();

	}
	
	public void update(ServiceEntity entity){
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}
	
}