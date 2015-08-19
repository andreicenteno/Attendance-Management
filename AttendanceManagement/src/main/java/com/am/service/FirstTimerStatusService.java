package com.am.service;
import java.util.List;

import com.am.model.FirstTimerStatus;

public interface FirstTimerStatusService {
	public void insert(FirstTimerStatus entity);
	public List<FirstTimerStatus> listFirstTimerStatus();
	public FirstTimerStatus getFirstTimerStatus(Long id);
	public void delete(FirstTimerStatus entity);
	public void update(FirstTimerStatus entity);
}