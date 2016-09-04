/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hiber.dao.GroupDAO;
import com.hiber.dao.UserDAO;
import com.hiber.model.Group;
import com.hiber.model.User;

/**
 *  Author : tungtt         
 * Sep 2, 2016
 */
@Controller
@RequestMapping(value="/account")
public class UserController {
	private final static Logger LOGGER = Logger.getLogger(UserController.class);
	@Autowired
	private GroupDAO groupDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView addForm(@RequestParam(value="group", required=false) Integer group) {
		ModelAndView mv = new ModelAndView("user.list", "command", new User());
		mv.addObject("groups", toGroupMap(groupDAO.list("")));
		mv.addObject("users", userDAO.list(group));
		return mv;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView addNew(@Valid @ModelAttribute("command") User user, BindingResult result) {
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("user.list", "command", user);
			mv.addObject("groups", toGroupMap(groupDAO.list("")));
			mv.addObject("errors", result);
			return mv;
		}
		
		userDAO.insert(user);
		LOGGER.info("add new user -----> " + user);
		return new ModelAndView("redirect:/account");
	}
	
	@RequestMapping(value="/detail/{username}")
	public ModelAndView detail(@PathVariable(value="username") String username) {
		ModelAndView mv = new ModelAndView("user.detail");
		mv.addObject("users", userDAO.get(username));
		return mv;
	}
	
	private Map<Integer, String> toGroupMap(List<Group> groups) {
		Map<Integer, String> map = new HashMap<>();
		groups.forEach(group -> map.put(group.getId(), group.getName()));
		return map;
	}
}
