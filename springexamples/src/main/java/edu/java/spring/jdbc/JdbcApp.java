/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  Author : tungtt         
 * Aug 21, 2016
 */
public class JdbcApp {
	private final static Logger LOGGER = Logger.getLogger(JdbcApp.class);
	
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		StudentJdbcDAO jdbc= (StudentJdbcDAO) context.getBean("studentJdbcDAO");
		LOGGER.info(" created bean " + jdbc);
		jdbc.insert("Tran Van A", 24);
//		jdbc.updateAgeByName("Tran Van A", 40);
//		jdbc.loadStudent("A").forEach(student -> LOGGER.info(student));
//		List<Student> students = new ArrayList<>();
//		students.add(new Student("Tran Thi A", 17));
//		students.add(new Student("Le Van L", 20));
//		students.add(new Student("Phan Thi Z", 25));
//		
//		int[] values = jdbc.add(students);
//		
//		for(int i = 0; i < values.length; i++) {
//			LOGGER.info("add record " + i + ": " + (values[i] == 0 ? "failed" : "success"));
//		}

//		jdbc.save("Tran Thi A", "23");
		
		LOGGER.info("Total students is " + jdbc.totalRecord());
		context.close();
	}
}
