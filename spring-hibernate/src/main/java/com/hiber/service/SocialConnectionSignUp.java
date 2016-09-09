/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.hiber.dao.StudentDAO;
import com.hiber.model.Student;

/**
 *  Author : tungtt         
 * Sep 9, 2016
 */
public class SocialConnectionSignUp implements ConnectionSignUp {
	private final static Logger LOGGER = Logger.getLogger(SocialConnectionSignUp.class);
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public String execute(final Connection<?> connection) {
		UserProfile userProfile = connection.fetchUserProfile();
		LOGGER.info(" ----> id " + userProfile.getId() + " name: " + userProfile.getName());
		
		Student user = studentDAO.get(userProfile.getEmail());
		if(user != null) return user.getUsername();
		
		user = new Student();
		user.setUsername(userProfile.getEmail());
		user.setPassword("123");
		user.setGroupId(1);
		user.setEmail(userProfile.getEmail());
		studentDAO.insert(user);
		
		return user.getUsername();
	}
}
