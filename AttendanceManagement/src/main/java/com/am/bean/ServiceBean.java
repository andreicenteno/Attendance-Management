package com.am.bean;

import java.sql.Timestamp;

public class ServiceBean{
	private long serviceId;
	private String serviceName;
	private String description;
	private Timestamp createTime;
	private Timestamp updateTime;
	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
		return "ServiceBean [serviceId=" + serviceId + ", serviceName="
				+ serviceName + ", description=" + description
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}