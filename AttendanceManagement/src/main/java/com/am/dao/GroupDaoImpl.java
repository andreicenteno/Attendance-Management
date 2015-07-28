package com.am.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.am.model.Group;


@Repository("groupDao")
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Group entity){
		Long id = (Long) sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		entity.setId(id);
	}

	@SuppressWarnings("unchecked")
	public List<Group> listGroup(){
		String sql = "SELECT * FROM asystem.groups order by update_time desc";
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
	    sqlQuery.addEntity(Group.class);
	    return sqlQuery.list();
	}
	
	public Group getGroup(Long id){
		return (Group) sessionFactory.getCurrentSession().get(Group.class, id);
	}
	
	public void delete(Group entity){
		sessionFactory.getCurrentSession().delete(entity);
		sessionFactory.getCurrentSession().flush();

	}
	
	public void update(Group entity){
		sessionFactory.getCurrentSession().update(entity);
		sessionFactory.getCurrentSession().flush();
	}
	
}