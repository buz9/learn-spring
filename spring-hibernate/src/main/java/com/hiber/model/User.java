/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.model;

/**
 *  Author : tungtt         
 * Sep 2, 2016
 */
public class User {
	private String username;
	private String password;
	private int age;
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
	
	public Group getGroup() {
		return group;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
}
