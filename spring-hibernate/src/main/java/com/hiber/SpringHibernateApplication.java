package com.hiber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringHibernateApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext tx = SpringApplication.run(SpringHibernateApplication.class, args);
		tx.start();
	}
}
