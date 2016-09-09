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
import com.hiber.dao.StudentDAO;
import com.hiber.model.Group;
import com.hiber.model.Student;

/**
 *  Author : tungtt         
 * Sep 2, 2016
 */
@Controller
@RequestMapping(value="/account")
public class StudentController {
	private final static Logger LOGGER = Logger.getLogger(StudentController.class);
	@Autowired
	private GroupDAO groupDAO;
	
	@Autowired
	private StudentDAO studentDAO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(value="q", required=false) Integer groupId, @RequestParam(value="page", required=false) Integer page) {
		ModelAndView mv = new ModelAndView("user.list", "command", new Student());
		mv.addObject("groups", toGroupMap(groupDAO.list("")));
		if(groupId != null && groupId > 0) { 
			mv.addObject("users", studentDAO.list(groupId));
		} else {
//			if(page == null) page = 1;
//			mv.addObject("users", studentDAO.page(page));
			mv.addObject("users", studentDAO.listUserByNativeSQL());
		}
		return mv;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView addNew(@Valid @ModelAttribute("command") Student user, BindingResult result) {
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("user.list", "command", user);
			mv.addObject("groups", toGroupMap(groupDAO.list("")));
			mv.addObject("errors", result);
			return mv;
		}
		
		studentDAO.insert(user);
		LOGGER.info("add new user -----> " + user);
		return new ModelAndView("redirect:/account");
	}
	
	@RequestMapping(value="/detail/{username}")
	public ModelAndView detail(@PathVariable(value="username") String username) {
		ModelAndView mv = new ModelAndView("user.detail");
		mv.addObject("user", studentDAO.get(username));
		return mv;
	}
	
	@RequestMapping(value="/delete-{name}", method=RequestMethod.GET)
	public String delete(@PathVariable(value="name") String name) {
		studentDAO.delete(name);
		return "redirect:/account";
	}
	
	private Map<Integer, String> toGroupMap(List<Group> groups) {
		Map<Integer, String> map = new HashMap<>();
		groups.forEach(group -> map.put(group.getId(), group.getName()));
		return map;
	}
	
	@RequestMapping("/batch-add")
	public String addRandom() {
		studentDAO.addBatch();
		return "redirect:/account";
	}
}
