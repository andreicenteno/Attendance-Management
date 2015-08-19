package com.am.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="first_timer_status", schema="asystem")
public class FirstTimerStatus implements Serializable{
	
private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name="first_timer_status_id", nullable=true, insertable=false, updatable=false)
	@Generated(GenerationTime.INSERT)
	@SequenceGenerator(name="first_timer_status_id_seq", sequenceName="asystem.first_timer_status_first_timer_status_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="first_timer_status_id_seq")
	private Long id;
	
	@Column(name="first_timer_status")
	private String firstTimerStatus;

	@Column(name="description")
	private String description;
	
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

	public String getFirstTimerStatus() {
		return firstTimerStatus;
	}

	public void setFirstTimerStatus(String firstTimerStatus) {
		this.firstTimerStatus = firstTimerStatus;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public String toString() {
		return "FirstTimerStatus [id=" + id + ", firstTimerStatus="
				+ firstTimerStatus + ", description=" + description
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", toString()=" + super.toString() + "]";
	}

	
	
	

	
	
	
	

	
}