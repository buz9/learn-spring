/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.hiber.dao.StudentDAO;
import com.hiber.model.Student;

/**
 *  Author : tungtt         
 * Sep 9, 2016
 */
@Component("userAuthProvider")
public class UserAuthProvider implements AuthenticationProvider {
	private final static Logger LOGGER = Logger.getLogger(UserAuthProvider.class);
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName().toString();
		Student student = studentDAO.get(username);
		if(student == null) return null;
		LOGGER.info(" ------> Found " + student + " by " + username);
		
		if(!student.getPassword().equals(auth.getCredentials())) return null;
		return successful(username, auth.getCredentials().toString(), student.getPassword());
	}

	private UsernamePasswordAuthenticationToken successful(String username, String password, String pass) {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
	}
	
	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
