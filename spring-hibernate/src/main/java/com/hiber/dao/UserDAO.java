/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.dao;

import java.util.List;

import com.hiber.model.Group;
import com.hiber.model.User;

/**
 *  Author : tungtt         
 * Sep 2, 2016
 */
public interface UserDAO {
	public User get(String username);
	public List<User> list(Integer group);
	public void insert(User user);
}
