package com.am.service;
import java.util.List;

import com.am.model.Attendees;

public interface AttendeesService {
	public void insert(Attendees entity);
	public List<Attendees> listAttendees();
	public Attendees getAttendees(Long id);
	public void delete(Attendees entity);
	public void update(Attendees entity);
	public List<Attendees> findAttendeesOnSundayService(long sunday_service_id);
}