package com.am.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name="first_timers", schema="asystem")
public class FirstTimer implements Serializable{
	
private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name="first_timer_id", nullable=true, insertable=false, updatable=false)
	@Generated(GenerationTime.INSERT)
	@SequenceGenerator(name="first_timer_id_seq", sequenceName="asystem.first_timers_first_timer_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="first_timer_id_seq")
	private Long id;
	
	@Column(name="remarks")
	private String remarks;

	@ManyToOne
	@JoinColumn(name = "sunday_service_id", nullable=false)
	private SundayService sundayService;
	
	@Column(name="attendees_id", nullable=true)
	private long attendeesId;

	@ManyToOne
	@JoinColumn(name = "first_timer_status_id", nullable=true)
	private FirstTimerStatus firstTimerStatus;
	
	@ManyToOne
	@JoinColumn(name = "guest_id", nullable=true)
	private Attendees attendeesGuest;
	
	@Generated(GenerationTime.INSERT)
	@Column(name="create_time" , nullable = false, insertable=false, updatable=false)
	private Timestamp createTime;
	
	@Generated(GenerationTime.ALWAYS)
	@Column(name="update_time",  nullable = false, insertable=false, updatable=false)
	private Timestamp updateTime;

	
	@Column(name="fname_invite", nullable = true)
	private String firstNameInvite;
	
	
	
	public FirstTimerStatus getFirstTimerStatus() {
		return firstTimerStatus;
	}

	public void setFirstTimerStatus(FirstTimerStatus firstTimerStatus) {
		this.firstTimerStatus = firstTimerStatus;
	}

	public String getFirstNameInvite() {
		return firstNameInvite;
	}

	public void setFirstNameInvite(String firstNameInvite) {
		this.firstNameInvite = firstNameInvite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public SundayService getSundayService() {
		return sundayService;
	}

	public void setSundayService(SundayService sundayService) {
		this.sundayService = sundayService;
	}

	

	public long getAttendeesId() {
		return attendeesId;
	}

	public void setAttendeesId(long attendeesId) {
		this.attendeesId = attendeesId;
	}

	public Attendees getAttendeesGuest() {
		return attendeesGuest;
	}

	public void setAttendeesGuest(Attendees attendeesGuest) {
		this.attendeesGuest = attendeesGuest;
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
		return "FirstTimer [id=" + id + ", remarks=" + remarks
				+ ", sundayService=" + sundayService + ", attendeesId="
				+ attendeesId + ", firstTimerStatus=" + firstTimerStatus
				+ ", attendeesGuest=" + attendeesGuest + ", createTime="
				+ createTime + ", updateTime=" + updateTime
				+ ", firstNameInvite=" + firstNameInvite + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
	
	

	
}