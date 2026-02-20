package com.kce.service;

import java.util.Date;
import java.util.List;

import com.kce.bean.Course;
import com.kce.bean.Enrollment;
import com.kce.dao.CourseDAO;
import com.kce.dao.EnrollmentDAO;
import com.kce.util.ActiveEnrollmentException;
import com.kce.util.CourseFullException;
import com.kce.util.ValidationException;

public class EnrollmentService {
	
	CourseDAO courseDAO=new CourseDAO();
	EnrollmentDAO enrollmentDAO=new EnrollmentDAO();
	
	public Course viewCourseDetail(String courseID) {
		if(courseID ==null || courseID.trim().isEmpty()) {
			System.out.println("NOT FOUND");
			return null;
		}
		return courseDAO.findCourse(courseID);
	}
	public List<Course> viewAllCourses(){
		return courseDAO.viewAllCourse();
		
	}
	public boolean addNewCourse(Course course) throws ValidationException {
		if(course.getCourseID()==null && course.getCourseTitle()==null) {
			throw new ValidationException();
		}
		if(course.getCredits()<=0 && course.getCapacity()<=0) {
			throw new ValidationException();
		}
		return courseDAO.insertCourse(course);
	}
	public boolean removeCourse(String courseID) throws ActiveEnrollmentException {
		Course c=courseDAO.findCourse(courseID);
		if(c==null) {
			System.out.print("Course not Found");
			return false;
		}if(c.getEnrolledCount()>0) {
		    throw new ActiveEnrollmentException();
		}
		return courseDAO.deleteCourse(courseID);
	}
	
	public boolean enrollStudent(String courseID,String studentID,String studentName) throws ValidationException, CourseFullException {
		if(courseID.isEmpty() || studentID.isEmpty() || studentName.isEmpty()) {
			throw new ValidationException();
		}
		if(courseDAO.findCourse(courseID)==null) {
			System.out.print("Course Not Found");
			return false;
		}
		if(courseDAO.findCourse(courseID).getEnrolledCount()>=courseDAO.findCourse(courseID).getCapacity()) {
			throw new CourseFullException();
		}
		int newEnrolled=courseDAO.findCourse(courseID).getEnrolledCount()+1;
		courseDAO.updateEnrolledCount(courseID, newEnrolled);
		
		Enrollment enroll=new Enrollment();
		
		enroll.setEnrollmentID(enrollmentDAO.generateEnrollmentID());
		enroll.setCourseID(courseID);
		enroll.setStudentID(studentID);
		enroll.setStudentName(studentName);
		enroll.setEnrollmentDate(new Date());
		enroll.setStatus("ENROLLED");
		return enrollmentDAO.recordEnrollment(enroll);
	}
	
	public boolean dropEnrollment(int id) throws Exception {

        Enrollment e = enrollmentDAO.findEnrollment(id);
        if (e == null) return false;

        Course c = courseDAO.findCourse(e.getCourseID());
        courseDAO.updateEnrolledCount(c.getCourseID(), c.getEnrolledCount() - 1);

        enrollmentDAO.closeEnrollment(id);
        return true;
    }
}

