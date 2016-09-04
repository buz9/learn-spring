/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.dao;

import java.util.List;

import com.hiber.model.Group;

/**
 *  Author : tungtt         
 * Sep 1, 2016
 */
public interface GroupDAO {
	public List<Group> list(String pattern);
	public Group get(int id);
	public void insert(Group group);
	public void delete(int id);
	public void update(Group group);
}
