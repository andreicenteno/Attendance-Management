package com.am.bean;

public class AttendeesViewBean{
	private long attendeesId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String address;
	private String contactNumber;
	private String birthday;
	private Boolean gender;
	private Boolean isMember;
	private long groupId;
	private String groupName;
	private long ministryId;
	private String ministryName;
	
	
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Boolean getIsMember() {
		return isMember;
	}
	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}
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
	@Override
	public String toString() {
		return "AttendeesViewBean [attendeesId=" + attendeesId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", address=" + address + ", contactNumber="
				+ contactNumber + ", birthday=" + birthday + ", gender="
				+ gender + ", isMember=" + isMember + ", groupId=" + groupId
				+ ", groupName=" + groupName + ", ministryId=" + ministryId
				+ ", ministryName=" + ministryName + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
}