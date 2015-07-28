package com.am.service;
import java.util.List;

import com.am.model.Ministry;

public interface MinistryService {
	public void insert(Ministry entity);
	public List<Ministry> listMinistry();
	public Ministry getMinistry(Long id);
	public void delete(Ministry entity);
	public void update(Ministry entity);
}