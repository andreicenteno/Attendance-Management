package com.am.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


@Entity
@Table(name="v_service_attendees", schema="asystem")
public class ServiceAttendanceView{
	
	@Id
	@Column(name="sunday_service_id", nullable=false, insertable=false, updatable=false)
	@Generated(GenerationTime.INSERT)	
	private long sundayServiceId;
	
	@Column(name="total")
	private long total;

	@Column(name="total_of_kkb")
	private long totalOfKkb;
	
	@Column(name="total_of_yam")
	private long totalOfYam;
	
	@Column(name="total_of_men")
	private long totalOfMen;
	
	@Column(name="total_of_women")
	private long totalOfWomen;
	
	@Column(name="total_of_children")
	private long totalOfChildren;
	
	@Column(name="total_of_kkb_male")
	private long totalOfKkbMale;
	
	@Column(name="total_of_kkb_female")
	private long totalOfKkbFemale;
	
	@Column(name="total_of_yam_male")
	private long totalOfYamMale;
	
	@Column(name="total_of_yam_female")
	private long totalOfYamFemale;
	
	@Column(name="total_of_children_male")
	private long totalOfChildrenMale;
	
	@Column(name="total_of_children_female")
	private long totalOfChildrenFemale;

	public long getSundayServiceId() {
		return sundayServiceId;
	}

	public void setSundayServiceId(long sundayServiceId) {
		this.sundayServiceId = sundayServiceId;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalOfKkb() {
		return totalOfKkb;
	}

	public void setTotalOfKkb(long totalOfKkb) {
		this.totalOfKkb = totalOfKkb;
	}

	public long getTotalOfYam() {
		return totalOfYam;
	}

	public void setTotalOfYam(long totalOfYam) {
		this.totalOfYam = totalOfYam;
	}

	public long getTotalOfMen() {
		return totalOfMen;
	}

	public void setTotalOfMen(long totalOfMen) {
		this.totalOfMen = totalOfMen;
	}

	public long getTotalOfWomen() {
		return totalOfWomen;
	}

	public void setTotalOfWomen(long totalOfWomen) {
		this.totalOfWomen = totalOfWomen;
	}

	public long getTotalOfChildren() {
		return totalOfChildren;
	}

	public void setTotalOfChildren(long totalOfChildren) {
		this.totalOfChildren = totalOfChildren;
	}

	public long getTotalOfKkbMale() {
		return totalOfKkbMale;
	}

	public void setTotalOfKkbMale(long totalOfKkbMale) {
		this.totalOfKkbMale = totalOfKkbMale;
	}

	public long getTotalOfKkbFemale() {
		return totalOfKkbFemale;
	}

	public void setTotalOfKkbFemale(long totalOfKkbFemale) {
		this.totalOfKkbFemale = totalOfKkbFemale;
	}

	public long getTotalOfYamMale() {
		return totalOfYamMale;
	}

	public void setTotalOfYamMale(long totalOfYamMale) {
		this.totalOfYamMale = totalOfYamMale;
	}

	public long getTotalOfYamFemale() {
		return totalOfYamFemale;
	}

	public void setTotalOfYamFemale(long totalOfYamFemale) {
		this.totalOfYamFemale = totalOfYamFemale;
	}

	public long getTotalOfChildrenMale() {
		return totalOfChildrenMale;
	}

	public void setTotalOfChildrenMale(long totalOfChildrenMale) {
		this.totalOfChildrenMale = totalOfChildrenMale;
	}

	public long getTotalOfChildrenFemale() {
		return totalOfChildrenFemale;
	}

	public void setTotalOfChildrenFemale(long totalOfChildrenFemale) {
		this.totalOfChildrenFemale = totalOfChildrenFemale;
	}

	
	@Override
	public String toString() {
		return "ServiceAttendanceView [sundayServiceId=" + sundayServiceId
				+ ", total=" + total + ", totalOfKkb=" + totalOfKkb
				+ ", totalOfYam=" + totalOfYam + ", totalOfMen=" + totalOfMen
				+ ", totalOfWomen=" + totalOfWomen + ", totalOfChildren="
				+ totalOfChildren + ", totalOfKkbMale=" + totalOfKkbMale
				+ ", totalOfKkbFemale=" + totalOfKkbFemale
				+ ", totalOfYamMale=" + totalOfYamMale + ", totalOfYamFemale="
				+ totalOfYamFemale + ", totalOfChildrenMale="
				+ totalOfChildrenMale + ", totalOfChildrenFemale="
				+ totalOfChildrenFemale + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
	
	

	
}