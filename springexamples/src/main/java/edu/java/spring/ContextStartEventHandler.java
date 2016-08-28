/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 *  Author : tungtt         
 * Aug 20, 2016
 */
public class ContextStartEventHandler implements ApplicationListener<ContextStartedEvent> {

	@Override
	public void onApplicationEvent(ContextStartedEvent e) {
		System.out.println("Context started at " + e.getTimestamp());
	}
	
}
