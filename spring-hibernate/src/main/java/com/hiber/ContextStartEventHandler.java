/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 *  Author : tungtt         
 * Sep 1, 2016
 */
@Component
public class ContextStartEventHandler implements ApplicationListener<ContextStartedEvent
> {
	private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void onApplicationEvent(ContextStartedEvent context) {
		try {
//			createTable("Groups", "create table Groups(id BIGINT PRIMARY "
//					+ "key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
//					+ "name VARCHAR(50))");
			createTable("Students", "create table Students("
						+ "username VARCHAR(50) primary key, "
						+ "password VARCHAR(50), "
						+ "email VARCHAR(100), "
						+ "age INTEGER, "
						+ "groupId BIGINT, "
						+ "CONSTRAINT GROUP_FK FOREIGN KEY (groupId) "
						+ "REFERENCES GROUPS(id))");
		} catch(Exception e) {
			LOGGER.error("Context start: " + e.getMessage());
		}
		LOGGER.info("Context start application " + dataSource);
	}
	
	private void createTable(String name, String script) throws SQLException {
		DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
		ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
		if(rs.next()) {
			LOGGER.info("Table " + rs.getString(name) + " already exists!");
			return;
		}
		dataSource.getConnection().createStatement().executeUpdate(script);
	}
}
