package com.am.service;
import java.util.List;

import com.am.model.FirstTimer;

public interface FirstTimerService {
	public void insert(FirstTimer entity);
	public List<FirstTimer> listFirstTimer();
	public FirstTimer getFirstTimer(Long id);
	public void delete(FirstTimer entity);
	public void update(FirstTimer entity);
	public List<FirstTimer> findFirstTimerBySundayServiceId(long sunday_service_id);
}