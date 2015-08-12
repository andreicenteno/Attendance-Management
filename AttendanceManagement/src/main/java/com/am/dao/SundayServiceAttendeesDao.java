package com.am.dao;
import java.util.List;

import com.am.model.SundayServiceAttendees;

public interface SundayServiceAttendeesDao {
	public void insert(SundayServiceAttendees entity);
	public List<SundayServiceAttendees> listSundayServiceAttendees();
	public SundayServiceAttendees getSundayServiceAttendees(Long id);
	public void delete(SundayServiceAttendees entity);
	public void update(SundayServiceAttendees entity);
	public List<SundayServiceAttendees> findSundayServiceAttendeesById(long sunday_service_id);
	public List<SundayServiceAttendees> findSundayServiceAttendeesByServiceIdGroupId(long sunday_service_id, long group_id);
	public List<SundayServiceAttendees> findSundayServiceAttendeesByName(String keyword, long sunday_service_id);
}