package com.hiber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:config.xml")
public class SpringHibernateApplication {

	public static void main(String[] args) {
//		ConfigurableApplicationContext ctx = SpringApplication.run(ServletInitializer.class, args);
//		ctx.start();
		SpringApplication.run(SpringHibernateApplication.class, args);
	}
	
}
