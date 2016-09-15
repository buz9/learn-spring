/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.microservice;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *  Author : tungtt         
 * Sep 4, 2016
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:/application.properties")
public class AppConfig extends WebMvcConfigurerAdapter {
	private final static Logger LOGGER = Logger.getLogger(AppConfig.class);
	
	@Autowired
	private Environment env;
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.microservice.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean
	public DataSource dataSource() {
		String url = env.getProperty("jdbc.url");
		String driverClassName = env.getProperty("jdbc.driverClassName");
		String username = env.getProperty("jdbc.username");
		String password = env.getProperty("jdbc.password");
		DriverManagerDataSource dataSource = new  DriverManagerDataSource(url);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		LOGGER.info("schema: " + dataSource.getSchema());
		return dataSource;
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.default_schema", env.getRequiredProperty("hibernate.default_schema"));
        return properties;
	}
}
