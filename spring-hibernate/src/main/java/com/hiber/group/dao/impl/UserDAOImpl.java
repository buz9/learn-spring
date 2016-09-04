/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.group.dao.impl;

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

import com.hiber.dao.UserDAO;
import com.hiber.model.User;

/**
 * Author : tungtt Sep 2, 2016
 */
@Component("userDAO")
public class UserDAOImpl implements UserDAO {
	private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@Override
	public List<User> list(Integer group) {
		Session session = sessionFactory.getObject().openSession();
		try {
			if(group == null || group < 0) {
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
			if(username == null || username.isEmpty()) {
				Query query = session.createQuery("from Users where name = :username");
				query.setParameter("username", username);
				return (User) query.uniqueResult();
			}
			
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("username", username));
			return (User)criteria.uniqueResult();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

}
