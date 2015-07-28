package com.am.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="attendees", schema="asystem")
public class Attendees implements Serializable{
	
private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name="attendees_id", nullable=true, insertable=false, updatable=false)
	@Generated(GenerationTime.INSERT)
	@SequenceGenerator(name="attendees_id_seq", sequenceName="asystem.attendees_attendees_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="attendees_id_seq")
	private Long id;
	
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
	
	@Column(name="is_first_timer")
	private Boolean isFirstTimer;
	
	@Column(name="first_timer_date")
	private Timestamp firstTimerDate;
	
	@Column(name="is_member")
	private Boolean isMember;
	
	@Column(name="member_date")
	private Timestamp memberDate;
	
	@ManyToOne
	@JoinColumn(name = "group_id", nullable=true)
	private Group group;
	
	
	@Generated(GenerationTime.INSERT)
	@Column(name="create_time" , nullable = false, insertable=false, updatable=false)
	private Timestamp createTime;
	
	@Generated(GenerationTime.ALWAYS)
	@Column(name="update_time",  nullable = false, insertable=false, updatable=false)
	private Timestamp updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Attendees [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName
				+ ", address=" + address + ", contactNumber=" + contactNumber
				+ ", birthday=" + birthday + ", gender=" + gender
				+ ", isFirstTimer=" + isFirstTimer + ", firstTimerDate="
				+ firstTimerDate + ", isMember=" + isMember + ", memberDate="
				+ memberDate + ", group=" + group + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", toString()="
				+ super.toString() + "]";
	}

	

	

	
	
	
	

	
}