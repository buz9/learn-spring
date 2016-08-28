/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;

/**
 *  Author : tungtt         
 * Aug 20, 2016
 */
public class HelloClazz implements DisposableBean {
	private String message;
	private List<JavaClazz> clazzes;
	
	public List<JavaClazz> getClazzes() {
		return clazzes;
	}
	
	public void setClazzes(List<JavaClazz> clazzes) {
		this.clazzes = clazzes;
	}
	
	public HelloClazz(int person) {
		message = "From Constructor: Say hello to " + person + " person(s)!";
	}
	
	public HelloClazz(HelloClazz clazz) {
		this.message = clazz.message;
	}
	
	public HelloClazz() {
		message = "From constructor: Say hello everyone1";
	}
	
	public void setMessage(String message) {
		this.message = "Call From Setter: " + message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void printMessage() {
		System.out.println("Your Message: " + message);
	}

	private void initMessage() {
		System.out.println("Calling init method ...");
		message = "From init method: Say hello bean!";
	}
	
	@Override
	public void destroy() throws Exception {
		message = null;
		System.out.println("message is null");
	}
}
