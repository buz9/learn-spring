/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hiber.dao.UserDAO;
import com.hiber.model.User;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * Author : tungtt Sep 2, 2016
 */
@Component("userDAO")
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO {
	private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@Override
	public List<User> list(Integer group) {
		Session session = sessionFactory.getObject().openSession();
		try {
			if (group == null || group < 0) {
				Query query = session.createQuery("from Users");
				return (List<User>) query.list();
			}

			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("groupId", group));
			return new ArrayList<User>(criteria.list());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(User user) {
		Session session = sessionFactory.getObject().openSession();
		try {
			session.save(user);
			session.flush();
			LOGGER.info("Save user " + user.getUsername() + " done!");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			session.close();
		}
	}

	@Override
	public User get(String username) {
		Session session = sessionFactory.getObject().openSession();
		try {
			Query query = session.createQuery("from Users");
//			query.setParameter("username", username);
//			 where username = :username
			System.out.println("size: " + query.list().size());
			return (User) query.uniqueResult();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

}
