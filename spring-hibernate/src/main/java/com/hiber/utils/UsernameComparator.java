/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.utils;

import java.util.Comparator;

import com.hiber.model.Student;

/**
 *  Author : tungtt         
 * Sep 9, 2016
 */
public class UsernameComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getUsername().compareTo(o2.getUsername());
	}

}
