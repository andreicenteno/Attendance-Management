package com.am.bean;

import java.sql.Timestamp;

public class FirstTimerStatusBean{
	private long firstTimerStatusId;
	private String firstTimerStatus;
	private String description;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	public long getFirstTimerStatusId() {
		return firstTimerStatusId;
	}
	public void setFirstTimerStatusId(long firstTimerStatusId) {
		this.firstTimerStatusId = firstTimerStatusId;
	}
	public String getFirstTimerStatus() {
		return firstTimerStatus;
	}
	public void setFirstTimerStatus(String firstTimerStatus) {
		this.firstTimerStatus = firstTimerStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return "FirstTimerStatusBean [firstTimerStatusId=" + firstTimerStatusId
				+ ", firstTimerStatus=" + firstTimerStatus + ", description="
				+ description + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", toString()=" + super.toString() + "]";
	}
	
	
	
}