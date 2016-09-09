/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
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
	private final static int SIZE_OF_PAGE = 2;
	
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

	@Override
	public void delete(String name) {
		Session session = sessionFactory.getObject().openSession();
		String hql = "delete from Students where username = :name";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			int result = query.executeUpdate();
			LOGGER.info("Rows affected: " + result + "\n\n");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public List<Student> listOlder() {
		Session session = sessionFactory.getObject().openSession();
		try {
			Criteria criteria = session.createCriteria(Student.class);
			criteria.add(Restrictions.gt("age", 50));
			return (List<Student>) criteria.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Student> page(int page) {
		Session session = sessionFactory.getObject().openSession();
		try {
			Criteria criteria = session.createCriteria(Student.class);
			int start = (page - 1) * SIZE_OF_PAGE;
			criteria.setFirstResult(start);
			criteria.setMaxResults(SIZE_OF_PAGE);
			return (List<Student>) criteria.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Student> listUserByNativeSQL() {
		Session session = sessionFactory.getObject().openSession();
		String sql = "select * from Students where age > :value";
		try {
			NativeQuery query = session.createSQLQuery(sql);
			query.setParameter("value", 50);
			query.addEntity(Student.class);
			return (List<Student>) query.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	
	public void addBatch() {
		Session session = sessionFactory.getObject().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for(int i = 0; i < 50; i++) {
				session.save(StudentFactory.generate(i+1));
			}
			session.flush();
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) tx.rollback();
			LOGGER.error(e, e);
		} finally {
			session.close();
		}
	}
}
