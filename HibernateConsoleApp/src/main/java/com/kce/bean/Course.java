package com.kce.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Course_tbl")
public class Course {
	@Id
	@Column(name="Course_Id")
	private String courseID;
	
	@Column(name="Course_title")
	private String courseTitle;
	
	@Column(name="Credits")
	private int credits;
	
	@Column(name="Capacity")
	private int capacity;
	
	@Column(name="Enrollment_count")
	private Integer enrolledCount;
	
	public String getCourseID() {
		return courseID;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public int getCredits() {
		return credits;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getEnrolledCount() {
		return enrolledCount;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setEnrolledCount(int enrolledCount) {
		this.enrolledCount = enrolledCount;
	}
	
}
