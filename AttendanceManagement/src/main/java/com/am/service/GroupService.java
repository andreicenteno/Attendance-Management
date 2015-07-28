package com.am.service;
import java.util.List;

import com.am.model.Group;

public interface GroupService {
	public void insert(Group entity);
	public List<Group> listGroup();
	public Group getGroup(Long id);
	public void delete(Group entity);
	public void update(Group entity);
}