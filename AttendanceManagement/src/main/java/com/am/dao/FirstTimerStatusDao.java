package com.am.dao;
import java.util.List;

import com.am.model.FirstTimerStatus;

public interface FirstTimerStatusDao {

	public void insert(FirstTimerStatus entity);
	public List<FirstTimerStatus> listFirstTimerStatus();
	public FirstTimerStatus getFirstTimerStatus(Long id);
	public void delete(FirstTimerStatus entity);
	public void update(FirstTimerStatus entity);
}