/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *  Author : tungtt         
 * Aug 20, 2016
 */
public class InitSampleBean implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String arg1) throws BeansException {
		System.out.println("Before initialization: " + arg1);
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String arg1) throws BeansException {
		System.out.println("After initialization: " + arg1);
		return bean;
	}

}
