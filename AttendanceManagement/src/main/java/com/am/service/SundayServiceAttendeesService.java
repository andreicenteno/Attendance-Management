package com.am.service;
import java.util.List;
import com.am.model.SundayServiceAttendees;

public interface SundayServiceAttendeesService {
	public void insert(SundayServiceAttendees entity);
	public List<SundayServiceAttendees> listSundayServiceAttendees();
	public SundayServiceAttendees getSundayServiceAttendees(Long id);
	public void delete(SundayServiceAttendees entity);
	public void update(SundayServiceAttendees entity);
	public List<SundayServiceAttendees> findSundayServiceAttendeesById(long sunday_service_id);
}