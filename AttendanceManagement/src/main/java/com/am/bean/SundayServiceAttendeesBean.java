package com.am.bean;

import java.sql.Timestamp;

public class SundayServiceAttendeesBean{
	private long sundayServiceAttendeesId;
	private SundayServiceBean sundayServiceBean;
	private AttendeesBean attendeesBean;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	public long getSundayServiceAttendeesId() {
		return sundayServiceAttendeesId;
	}
	public void setSundayServiceAttendeesId(long sundayServiceAttendeesId) {
		this.sundayServiceAttendeesId = sundayServiceAttendeesId;
	}
	public SundayServiceBean getSundayServiceBean() {
		return sundayServiceBean;
	}
	public void setSundayServiceBean(SundayServiceBean sundayServiceBean) {
		this.sundayServiceBean = sundayServiceBean;
	}
	public AttendeesBean getAttendeesBean() {
		return attendeesBean;
	}
	public void setAttendeesBean(AttendeesBean attendeesBean) {
		this.attendeesBean = attendeesBean;
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
	@Override
	public String toString() {
		return "SundayServiceAttendeesBean [sundayServiceAttendeesId="
				+ sundayServiceAttendeesId + ", sundayServiceBean="
				+ sundayServiceBean + ", attendeesBean=" + attendeesBean
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
	
	
	
	
}