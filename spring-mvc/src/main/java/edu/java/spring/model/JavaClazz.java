/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package edu.java.spring.model;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *  Author : tungtt         
 * Aug 20, 2016
 */
@XmlRootElement(name="clazz")
public class JavaClazz {
	private List<Student> students;
	
	public JavaClazz() {
	}
	
	public JavaClazz(List<Student> students) {
		this.students = students;
	}

	@XmlElements(@XmlElement(name="student", type=Student.class))
	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
