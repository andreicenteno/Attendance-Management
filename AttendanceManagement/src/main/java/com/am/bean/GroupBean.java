package com.am.bean;

import java.sql.Timestamp;

public class GroupBean{
	private long groupId;
	private String groupName;
	private String description;
	private Timestamp createTime;
	private Timestamp updateTime;
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
		return "GroupBean [groupId=" + groupId + ", groupName=" + groupName
				+ ", description=" + description + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}