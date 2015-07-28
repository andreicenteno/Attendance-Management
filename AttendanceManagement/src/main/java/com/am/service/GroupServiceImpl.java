package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.am.dao.GroupDao;
import com.am.model.Group;


@Service("groupService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDao groupDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void insert(Group entity){
		groupDao.insert(entity);
	}
	
	public Group getGroup(Long id){
		return groupDao.getGroup(id);
	}
	
	public void delete(Group entity){
		groupDao.delete(entity);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Group entity){
		groupDao.update(entity);
	}
	
	
	public List<Group> listGroup(){
		return groupDao.listGroup();
	}
}