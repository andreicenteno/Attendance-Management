package com.am.dao;
import java.util.List;

import com.am.model.AttendeesView;

public interface AttendeesViewDao {
	public List<AttendeesView> listAttendeesSummaryView();
	public List<AttendeesView> listAttendeesViewByGroup(long group_id);
	public List<AttendeesView> listAttendeesViewwByGroupMinistry(long group_id, long ministry_id);
	public List<AttendeesView> listAttendeesViewByGroupMinistryGender(long group_id, long ministry_id, Boolean gender);
	
	public List<AttendeesView> listAttendeesViewByMinistry(long ministry_id);
	public List<AttendeesView> listAttendeesViewByGender(Boolean gender);
	public List<AttendeesView> listAttendeesViewByGenderMinistry(Boolean gender, long ministry_id);
	public List<AttendeesView> listAttendeesViewByGenderGroup(Boolean gender, long group_id);
}