package com.am.bean;

import java.sql.Timestamp;

public class SundayServiceBean{
	private long sundayServiceId;
	private String serviceTitle;
	private String description;
	private ServiceBean serviceBean;
	private String createTime;
	private Timestamp updateTime;
	private String keywords;
	
	
	
	
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public long getSundayServiceId() {
		return sundayServiceId;
	}
	public void setSundayServiceId(long sundayServiceId) {
		this.sundayServiceId = sundayServiceId;
	}
	public String getServiceTitle() {
		return serviceTitle;
	}
	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ServiceBean getServiceBean() {
		return serviceBean;
	}
	public void setServiceBean(ServiceBean serviceBean) {
		this.serviceBean = serviceBean;
	}
	
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
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
		return "SundayServiceBean [sundayServiceId=" + sundayServiceId
				+ ", servicetitle=" + serviceTitle + ", description="
				+ description + ", serviceBean=" + serviceBean
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
	
}