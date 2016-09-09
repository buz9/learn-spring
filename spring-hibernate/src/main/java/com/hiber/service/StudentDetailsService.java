/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hiber.dao.StudentDAO;
import com.hiber.model.Student;

/**
 * Author : tungtt Sep 9, 2016
 */
@Service
public class StudentDetailsService implements UserDetailsService {

	@Autowired
	private StudentDAO studentDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = studentDAO.get(username);
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(student.getUsername(),
				student.getPassword(), true, true, true, true, authorities);
	}

}
