package com.am.dao;
import java.util.List;

import com.am.model.ServiceEntity;

public interface ServiceDao {
	public void insert(ServiceEntity entity);
	public List<ServiceEntity> listService();
	public ServiceEntity getService(Long id);
	public void delete(ServiceEntity entity);
	public void update(ServiceEntity entity);
}