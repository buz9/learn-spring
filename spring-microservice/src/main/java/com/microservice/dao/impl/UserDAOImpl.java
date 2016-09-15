/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.microservice.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import com.microservice.dao.UserDAO;
import com.microservice.model.User;

/**
 * Author : tungtt Sep 2, 2016
 */
@Component("userDAO")
public class UserDAOImpl implements UserDAO {
	private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

	private LocalSessionFactoryBean sessionFactory;

	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<User> list() {
		Session session = sessionFactory.getObject().openSession();
		try {
			Criteria criteria = session.createCriteria(User.class);
			return (List<User>) criteria.list();
		} finally {
			session.close();
		}
	}

	@Override
	public String insert(User user) {
		Session session = sessionFactory.getObject().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Serializable value = session.save(user);
			tx.commit();
			LOGGER.info("Save user " + user.getUsername() + " done!");
			return value.toString();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	
	@Override
	public User get(String username) {
		Session session = sessionFactory.getObject().openSession();
		try {
			Criteria criteria = session.createCriteria(User.class);
			 criteria.add(Restrictions.eq("username", username));
			return (User) criteria.uniqueResult();
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
		try {
			 User user = session.get(User.class, name);
			 if(user != null) session.delete(user);
			 session.flush();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
	}
}