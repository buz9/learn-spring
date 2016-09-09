/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import com.hiber.dao.StudentDAO;
import com.hiber.model.Student;

/**
 * Author : tungtt Sep 2, 2016
 */
@Component("userDAO")
public class StudentDAOImpl implements StudentDAO {
	private final static Logger LOGGER = Logger.getLogger(StudentDAOImpl.class);

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@Override
	public List<Student> list(Integer groupId) {
		Session session = sessionFactory.getObject().openSession();
		try {
			Criteria criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.eq("groupId", groupId));
			return (List<Student>) criteria.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(Student user) {
		Session session = sessionFactory.getObject().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(user);
			tx.commit();
			LOGGER.info("Save user " + user.getUsername() + " done!");
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public Student get(String username) {
		Session session = sessionFactory.getObject().openSession();
		try {
//			Query query = session.createQuery("from Students where username = :username");
//			query.setParameter("username", username);
			Criteria criteria = session.createCriteria(Student.class);
			 criteria.add(Restrictions.eq("username", username));
			return (Student) criteria.uniqueResult();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Student> list() {
		Session session = sessionFactory.getObject().openSession();
		try {
//			Query query = session.createQuery("from Students");
			Criteria criteria = session.createCriteria(Student.class);
			return (List<Student>) criteria.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
}
