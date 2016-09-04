/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *  Author : tungtt         
 * Aug 28, 2016
 */
@Controller
public class LoginController {
	@RequestMapping("/login")
	ModelAndView login(@RequestParam(value="error", required=false) String error) {
		ModelAndView mv = new ModelAndView("login");
		if(error != null) mv.addObject("error", "Sai tên đăng nhập hoặc mật khẩu!");
		return mv;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(req, resp, auth);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/user")
	ModelAndView forUser() {
		ModelAndView mv = new ModelAndView("index");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		mv.addObject("message", "Hello User " + auth.getName());
		return mv;
	}
	
	
}
