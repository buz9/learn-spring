/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *  Author : tungtt         
 * Aug 20, 2016
 */
@Configuration
public class AppConfig {
	
	@Bean(name="bean2")
	@Scope("singleton")
	public HelloClazz getHelloBean() {
		HelloClazz bean = new HelloClazz();
		bean.setMessage("hahaha");
		return bean;
	}
}
