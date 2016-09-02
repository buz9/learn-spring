/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.sboot.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *  Author : tungtt         
 * Aug 28, 2016
 */
@Controller
public class HomeController {
	private final static Logger logger = Logger.getLogger(HomeController.class);
	
	@RequestMapping("/")
	ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("message", "Hello World");
		logger.info("ok");
		return mv;
	}
}
