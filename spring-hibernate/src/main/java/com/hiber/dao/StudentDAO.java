/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.dao;

import java.util.List;

import com.hiber.model.Student;

/**
 *  Author : tungtt         
 * Sep 2, 2016
 */
public interface StudentDAO {
	public Student get(String username);
	public void delete(String name);
	public void insert(Student user);
	public List<Student> list();
	public List<Student> list(Integer group);
	public List<Student> listOlder();
	public List<Student> page(int page);
	public List<Student> listUserByNativeSQL();
	public void addBatch();
}
