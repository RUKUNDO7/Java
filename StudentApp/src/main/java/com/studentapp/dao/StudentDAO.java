package com.studentapp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.studentapp.model.Student;
import com.studentapp.util.HibernateUtil;

public class StudentDAO {

    public void saveStudent(Student student) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Open session and begin transaction
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Save student object
            session.save(student);

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback if something went wrong
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Always close session
            if (session != null) {
                session.close();
            }
        }
    }
}
