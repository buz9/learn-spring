/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.dao.impl;

import java.util.Random;

import com.hiber.model.Student;

/**
 *  Author : tungtt         
 * Sep 9, 2016
 */
public class StudentFactory {
	public static Student generate(int index) {
		Student student = new Student();
		Random random = new Random();
		student.setUsername("username-random" + index);
		student.setPassword("password" + random.nextInt() + 1);
		student.setEmail("name" + random.nextInt() + 1 + "@gmail.com");
		student.setAge(random.nextInt(100) + 1);
		student.setGroupId(102);
		return student;
	}
}
