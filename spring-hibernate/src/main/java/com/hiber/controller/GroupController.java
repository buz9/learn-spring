/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.controller;

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
import com.hiber.model.Group;

/**
 *  Author : tungtt         
 * Sep 1, 2016
 */
@Controller
@RequestMapping(value="/group")
public class GroupController {
	private final static Logger LOGGER = Logger.getLogger(GroupController.class);
	
	@Autowired
	private GroupDAO groupDAO;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addNew(@Valid @ModelAttribute("command") Group group, BindingResult result) {
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("group.list", "command", group);
			mv.addObject("error", result);
			return mv;
		}
		
			if(group.getId() > 0) {
				groupDAO.insert(group);
				LOGGER.info("add new group -----> " + group);
			} else {
				groupDAO.update(group);
				LOGGER.info("save group -----> " + group);
			}
			
		return new ModelAndView("group.list", "groups", groupDAO.list(""));
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(value="q", required=false) String pattern) {
		ModelAndView mv = new ModelAndView("group.list", "command", new Group());
		mv.addObject("groups", groupDAO.list(pattern));
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id) {
		if(id == null) return new ModelAndView("redirect:/group");
		groupDAO.delete(id);
		return new ModelAndView("redirect:/group");
	}
	
	@RequestMapping(value="edit")
	public ModelAndView updateForm(@RequestParam(value="id", required=true) Integer id) {
		Group group = groupDAO.get(id);
		if(group == null) return new ModelAndView("redirect:/group");
		
		ModelAndView mv = new ModelAndView("group.list", "command", group);
		mv.addObject("groupName", group.getName());
		mv.addObject("groups", groupDAO.list(""));
		return mv;
	}
}
