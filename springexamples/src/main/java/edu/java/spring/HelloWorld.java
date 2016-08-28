/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

/**
 *  Author : tungtt         
 * Aug 20, 2016
 */
public class HelloWorld {
	private final static Logger LOGGER = Logger.getLogger(HelloWorld.class);
	private String message;
	
	@Autowired(required=true)
	@Qualifier("helloJavaClazz2")
	private HelloClazz clazz;
	
	public void setClazz(HelloClazz clazz) {
		this.clazz = clazz;
	}
	
	public HelloWorld() {}

	@Required
	public void setMessage(String message) {
		this.message = message;
	}
	
	public HelloWorld(String name, HelloClazz clazz) {
		message = "From HelloWorld constructor: " + name + " : " + clazz.getMessage();
	}
	
	public void sayHello() {
		clazz.printMessage();
		LOGGER.info("From HelloWorld: " + message);
	}
}
