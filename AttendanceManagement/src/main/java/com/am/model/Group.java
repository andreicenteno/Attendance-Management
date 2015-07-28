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
@Table(name="groups", schema="asystem")
public class Group implements Serializable{
	
private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name="group_id", nullable=true, insertable=false, updatable=false)
	@Generated(GenerationTime.INSERT)
	@SequenceGenerator(name="group_id_seq", sequenceName="asystem.groups_group_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="group_id_seq")
	private Long id;
	
	@Column(name="group_name")
	private String groupName;

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	
	
	

	
	
	
	

	
}