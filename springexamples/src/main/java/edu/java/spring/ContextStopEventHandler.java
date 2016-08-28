/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 *  Author : tungtt         
 * Aug 20, 2016
 */
public class ContextStopEventHandler implements ApplicationListener<ContextStoppedEvent> {

	@Override
	public void onApplicationEvent(ContextStoppedEvent e) {
		System.out.println("Stop: " + e.getTimestamp());
	}

}
