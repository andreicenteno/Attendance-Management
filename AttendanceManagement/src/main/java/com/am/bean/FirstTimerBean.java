package com.am.bean;

import java.sql.Timestamp;

public class FirstTimerBean{
	private long first_timer_id;
	private SundayServiceBean sundayServiceBean;
	private AttendeesBean attendeesBean;
	private AttendeesBean guestBean;
	private String remarks;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	
	
	public SundayServiceBean getSundayServiceBean() {
		return sundayServiceBean;
	}
	public void setSundayServiceBean(SundayServiceBean sundayServiceBean) {
		this.sundayServiceBean = sundayServiceBean;
	}
	public long getFirst_timer_id() {
		return first_timer_id;
	}
	public void setFirst_timer_id(long first_timer_id) {
		this.first_timer_id = first_timer_id;
	}
	
	
	
	public AttendeesBean getAttendeesBean() {
		return attendeesBean;
	}
	public void setAttendeesBean(AttendeesBean attendeesBean) {
		this.attendeesBean = attendeesBean;
	}
	public AttendeesBean getGuestBean() {
		return guestBean;
	}
	public void setGuestBean(AttendeesBean guestBean) {
		this.guestBean = guestBean;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	
	
	
	
	
	
}