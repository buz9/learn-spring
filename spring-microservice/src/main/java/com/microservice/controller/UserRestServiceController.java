/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.dao.GroupDAO;
import com.microservice.dao.UserDAO;
import com.microservice.model.Group;
import com.microservice.model.User;

/**
 * Author : tungtt Sep 4, 2016
 */
@RestController
public class UserRestServiceController {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private GroupDAO groupDAO;

	@RequestMapping("/list/users")
	public List<User> listUser() {
		return userDAO.list();
	}

	@RequestMapping("/list/groups")
	public Group[] listGroup() {
		return groupDAO.list().toArray(new Group[10]);
	}

	@RequestMapping(value="/add/user", method=RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	public String addUser(@RequestBody User user) {
		return userDAO.insert(user);
	}
	
	@RequestMapping(value="/get/user/{name}", method=RequestMethod.GET)
	public User getUser(@PathVariable(value="name") String name) {
		return userDAO.get(name);
	}
	
	@RequestMapping(value="/delete/user/{name}", method=RequestMethod.GET)
	public void delUser(@PathVariable(value="name") String name) {
		userDAO.delete(name);
	}
}
