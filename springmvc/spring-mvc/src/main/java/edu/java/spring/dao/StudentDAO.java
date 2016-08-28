/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring.dao;

import java.util.List;

import edu.java.spring.model.Student;

/**
 *  Author : tungtt         
 * Aug 23, 2016
 */
public interface StudentDAO {
	public List<Student> list();
	public List<Student> list(String name);
	public void insert(Student student);
	public void delete(Integer id);
	public Student getById(Integer id);
	public void update(Student student);
}
