/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *  Author : tungtt         
 * Sep 2, 2016
 */
@Entity
@Table(name="Students", uniqueConstraints={@UniqueConstraint(columnNames="username")})
public class Student implements Comparable<Student> {
	@Id
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="age")
	private int age;
	
	@Column(name="groupId", nullable=false)
	private int groupId;
	
	private Group group;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getGroupId() {
		return groupId;
	}
	
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public int compareTo(Student other) {
		return age - other.age;
	}
}
