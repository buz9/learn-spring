/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *  Author : tungtt         
 * Sep 1, 2016
 */
@Entity(name="Groups")
@Table(name="Groups", uniqueConstraints={@UniqueConstraint(columnNames="id")})
public class Group {
	private int id;
	private String name;
	private List<User> users;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name", nullable=false, length=50)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="groupId")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
