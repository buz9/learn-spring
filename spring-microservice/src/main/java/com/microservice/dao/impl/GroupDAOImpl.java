/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.microservice.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import com.microservice.dao.GroupDAO;
import com.microservice.model.Group;

/**
 *  Author : tungtt         
 * Sep 1, 2016
 */
@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {
	private final static Logger LOGGER = Logger.getLogger(GroupDAOImpl.class);
	
	private LocalSessionFactoryBean sessionFactory;
	
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insert(Group group) {
		Session session = sessionFactory.getObject().openSession();
		try {
			session.save(group);
			session.flush();
			LOGGER.info("Save group " + group.getName() + " done!");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			session.close();
		}
	}

	@Override
	public List<Group> list() {
		Session session = sessionFactory.getObject().openSession();
		try {
			Query query = session.createQuery("from Groups");
			return (List<Group>) query.list();

//			Criteria criteria = session.createCriteria(Group.class);
//			criteria.add(Restrictions.like("name", "%" + pattern + "%", MatchMode.ANYWHERE));
//			return new ArrayList<Group>(criteria.list());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getObject().openSession();
		Group group = session.get(Group.class, id);
		if(group == null) return;
		try {
			session.delete(group);
			session.flush();
			LOGGER.info("Delete group " + group.getName() + " successful!");
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Group group) {
		Session session = sessionFactory.getObject().openSession();
		try {
			session.save(group);
			session.flush();
			LOGGER.info("Update group " + group.getName() + " successful!");
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public Group get(int id) {
		Session session = sessionFactory.getObject().openSession();
		Group group = (Group) session.createQuery("from Groups where id = " + id).uniqueResult();
		try {
			return group;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
}
