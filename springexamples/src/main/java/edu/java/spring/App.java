package edu.java.spring;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	private final static Logger LOGGER = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//		JavaClazz obj = (JavaClazz) context.getBean("jee01");
//		LOGGER.info("Map Implement is " + obj.getStudents().getClass());
//		LOGGER.info("There are " + obj.getStudents().size() + " in the class");
//		
//		HelloClazz clazz = (HelloClazz) context.getBean("helloJavaClazz");
//		LOGGER.info("Total classes is " + clazz.getClazzes().size());
//		((HelloWorld)context.getBean("helloWorld")).sayHello();;
		context.start();
		context.stop();
	}
}
