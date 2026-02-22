package com.kce.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.kce.bean.Enrollment;
import com.kce.util.HibernateUtil;

public class EnrollmentDAO {
	
    public boolean recordEnrollment(Enrollment enroll) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(enroll);
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
    public Enrollment findEnrollment(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Enrollment enrollment = session.get(Enrollment.class, id);
        session.close();
        return enrollment;
    }
    public boolean closeEnrollment(int enrollmentID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            Enrollment e = session.get(Enrollment.class, enrollmentID);
            if (e != null) {
                e.setStatus("DROPPED");
                session.merge(e);
;                tx.commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
