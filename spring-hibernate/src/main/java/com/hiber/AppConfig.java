/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber;

import java.io.IOException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

/**
 * Author : tungtt Aug 28, 2016
 */
@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private DataSource dataSource;

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions("/tiles/definitions.xml");
		return configurer;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:User.xml");
		
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource);
		localSessionFactoryBean.setPackagesToScan("com.hiber.model");
		localSessionFactoryBean.setMappingLocations(resource);
		return localSessionFactoryBean;
	}
}
