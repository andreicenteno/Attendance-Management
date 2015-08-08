package com.am.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
	
	@ManyToOne
	@JoinColumn(name = "attendees_id", nullable=true)
	private Attendees attendees;

	@ManyToOne
	@JoinColumn(name = "guest_id", nullable=true)
	private Attendees attendeesGuest;
	
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

	public Attendees getAttendees() {
		return attendees;
	}

	public void setAttendees(Attendees attendees) {
		this.attendees = attendees;
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

	
	
	
	
	

	
}