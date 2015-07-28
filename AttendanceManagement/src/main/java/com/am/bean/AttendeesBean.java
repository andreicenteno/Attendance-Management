package com.am.bean;

import java.sql.Timestamp;

public class AttendeesBean{
	private long attendeesId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String address;
	private String contactNumber;
	private String birthday;
	private Boolean gender;
	private Boolean isFirstTimer;
	private Timestamp firstTimerDate;
	private Boolean isMember;
	private Timestamp memberDate;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public long getAttendeesId() {
		return attendeesId;
	}
	public void setAttendeesId(long attendeesId) {
		this.attendeesId = attendeesId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Boolean getIsFirstTimer() {
		return isFirstTimer;
	}
	public void setIsFirstTimer(Boolean isFirstTimer) {
		this.isFirstTimer = isFirstTimer;
	}
	public Timestamp getFirstTimerDate() {
		return firstTimerDate;
	}
	public void setFirstTimerDate(Timestamp firstTimerDate) {
		this.firstTimerDate = firstTimerDate;
	}
	public Boolean getIsMember() {
		return isMember;
	}
	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}
	public Timestamp getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(Timestamp memberDate) {
		this.memberDate = memberDate;
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
		return "AttendeesBean [attendeesId=" + attendeesId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", address=" + address + ", contactNumber="
				+ contactNumber + ", birthday=" + birthday + ", gender="
				+ gender + ", isFirstTimer=" + isFirstTimer
				+ ", firstTimerDate=" + firstTimerDate + ", isMember="
				+ isMember + ", memberDate=" + memberDate + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}