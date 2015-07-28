package com.am.bean;

import java.sql.Timestamp;

public class MinistryBean{
	private long ministryId;
	private String ministryName;
	private String description;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	public long getMinistryId() {
		return ministryId;
	}
	public void setMinistryId(long ministryId) {
		this.ministryId = ministryId;
	}
	public String getMinistryName() {
		return ministryName;
	}
	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
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
		return "MinistryBean [ministryId=" + ministryId + ", ministryName="
				+ ministryName + ", description=" + description
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
	
}