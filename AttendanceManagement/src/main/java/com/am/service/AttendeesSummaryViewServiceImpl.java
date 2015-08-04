package com.am.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.am.dao.AttendeesSummaryViewDao;
import com.am.model.AttendeesSummaryView;



@Service("attendeesSummaryViewService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AttendeesSummaryViewServiceImpl implements AttendeesSummaryViewService{

	@Autowired
	private AttendeesSummaryViewDao  attendeesSummaryViewDao;
	
	public List<AttendeesSummaryView> listAttendeesSummaryView(){
		return attendeesSummaryViewDao.listAttendeesSummaryView();
	}
	
	
}