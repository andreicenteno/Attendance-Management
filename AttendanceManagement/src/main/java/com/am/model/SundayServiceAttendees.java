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
@Table(name="sunday_service_attendees", schema="asystem")
public class SundayServiceAttendees implements Serializable{
	
private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name="sunday_service_attendees_id", nullable=true, insertable=false, updatable=false)
	@Generated(GenerationTime.INSERT)
	@SequenceGenerator(name="sunday_service_attendees_id_seq", sequenceName="asystem.sunday_service_attendees_sunday_service_attendees_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sunday_service_attendees_id_seq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "sunday_service_id", nullable=true)
	private SundayService sundayService;
	
	@ManyToOne
	@JoinColumn(name = "attendees_id", nullable=true)
	private Attendees attendees;
	
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
		return "SundayServiceAttendees [id=" + id + ", sundayService="
				+ sundayService + ", attendees=" + attendees + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", toString()="
				+ super.toString() + "]";
	}

	
	
	

	
}