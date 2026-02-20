package com.kce.bean;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Enrollment_tbl1")
public class Enrollment {
	@Id
	@Column(name="Enrollment_Id")
	private int enrollmentID;
	
	@Column(name="Course_Id")
	private String courseID;
	
	@Column(name="Student_Id")
	private String studentID;
	
	@Column(name="Student_name")
	private String studentName;
	
	@Column(name="Enrollment_Date")
	private Date enrollmentDate;
	
	@Column(name="Status")
	private String status;
	
	public int getEnrollmentID() {
		return enrollmentID;
	}
	public String getCourseID() {
		return courseID;
	}
	public String getStudentID() {
		return studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public Date getEnrollmentDate() {
		return enrollmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
