/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *  Author : tungtt         
 * Sep 4, 2016
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IOException, SQLException {
		Resource resource = resourceLoader.getResource("classpath:Student.xml");
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource);
		localSessionFactoryBean.setPackagesToScan("com.hiber.model");
		localSessionFactoryBean.setMappingLocations(resource);
		return localSessionFactoryBean;
	}
}
