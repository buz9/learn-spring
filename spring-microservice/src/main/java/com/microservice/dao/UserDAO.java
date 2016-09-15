/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.microservice.dao;

import java.util.List;

import com.microservice.model.User;

/**
 *  Author : tungtt         
 * Sep 2, 2016
 */
public interface UserDAO {
	public List<User> list();
	public String insert(User user);
	public User get(String username);
	public void delete(String name);
}
