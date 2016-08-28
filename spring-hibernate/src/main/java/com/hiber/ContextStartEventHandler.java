/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber;

import javax.activation.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 *  Author : tungtt         
 * Aug 28, 2016
 */
public class ContextStartEventHandler implements ApplicationListener<ContextStartedEvent> {

	private final static Logger logger = Logger.getLogger(ContextStartEventHandler.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void onApplicationEvent(ContextStartedEvent arg0) {
		logger.info("context start application: " + dataSource);
	}

}
