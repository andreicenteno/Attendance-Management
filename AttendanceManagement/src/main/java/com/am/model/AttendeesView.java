package com.am.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="v_attendees", schema="asystem")
public class AttendeesView implements Serializable{
	
private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name="attendees_id", nullable=false, insertable=false, updatable=false)
	@Generated(GenerationTime.INSERT)	
	private long attendeesId;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="contact_number")
	private String contactNumber;
	
	@Column(name="birthday")
	private Date birthday;
	
	@Column(name="gender")
	private Boolean gender;
	
	@Column(name="is_member")
	private Boolean isMember;
	
	
	@Column(name="group_id")
	private long groupId;
	
	@Column(name="year_old")
	private double yearOld;

	@Column(name="group_name")
	private String groupName;

	
	@Column(name="ministry_id")
	private long ministryId;
	
	@Column(name="ministry_name")
	private String ministryName;

	
	
	public double getYearOld() {
		return yearOld;
	}

	public void setYearOld(double yearOld) {
		this.yearOld = yearOld;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	@Override
	public String toString() {
		return "AttendeesView [attendeesId=" + attendeesId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", address=" + address + ", contactNumber="
				+ contactNumber + ", birthday=" + birthday + ", gender="
				+ gender + ", isMember=" + isMember + ", groupId=" + groupId
				+ ", groupName=" + groupName + ", ministryId=" + ministryId
				+ ", ministryName=" + ministryName + ", toString()="
				+ super.toString() + "]";
	}

	
}