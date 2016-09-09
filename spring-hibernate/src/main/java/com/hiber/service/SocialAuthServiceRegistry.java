/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 *  Author : tungtt         
 * Sep 9, 2016
 */
public class SocialAuthServiceRegistry extends SocialAuthenticationServiceRegistry {
	private List<SocialAuthenticationService<?>> authenticationServices;
	
	public SocialAuthServiceRegistry(List<SocialAuthenticationService<?>> authenticationServices) {
		this.authenticationServices = authenticationServices;
	}
	
	@PostConstruct
	public void init() {
		if(authenticationServices == null) return;
		authenticationServices.forEach(auth -> super.addAuthenticationService(auth));
	}
}
