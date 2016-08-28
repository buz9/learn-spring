/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

/**
 *  Author : tungtt         
 * Aug 23, 2016
 */
public class AppContextLoaderListener extends ContextLoaderListener {
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println(" ----> Da huy ung dung");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println(" -----> Da khoi tao ung dung");
	}
}
