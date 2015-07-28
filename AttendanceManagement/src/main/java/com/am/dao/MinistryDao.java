package com.am.dao;
import java.util.List;

import com.am.model.Ministry;

public interface MinistryDao {

	public void insert(Ministry entity);
	public List<Ministry> listMinistry();
	public Ministry getMinistry(Long id);
	public void delete(Ministry entity);
	public void update(Ministry entity);
}