/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  Author : tungtt         
 * Aug 20, 2016
 */
public class App2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class);
		ctx.refresh();
		HelloClazz myBean = (HelloClazz) ctx.getBean("bean2");
		myBean.printMessage();
	}
}
