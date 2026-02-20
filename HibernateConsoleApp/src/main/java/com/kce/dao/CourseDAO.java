package com.kce.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.kce.bean.Course;
import com.kce.util.HibernateUtil;

public class CourseDAO {
    public Course findCourse(String courseID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = session.get(Course.class, courseID);
        session.close();
        return course;
    }
    public List<Course> viewAllCourse() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Course> query = session.createQuery("from Course", Course.class);
        List<Course> list = query.list();
        session.close();
        return list;
    }
    public boolean insertCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(course);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
    public boolean updateEnrolledCount(String courseID, int newCount) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Course c = session.get(Course.class, courseID);
            if (c != null) {
                c.setEnrolledCount(newCount);
                session.merge(c);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
    public boolean deleteCourse(String courseID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Course c = session.get(Course.class, courseID);
            if (c != null) {
                session.remove(c);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
