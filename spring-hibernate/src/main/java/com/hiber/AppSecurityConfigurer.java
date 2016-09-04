/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Author : tungtt Aug 28, 2016
 */
@EnableWebSecurity
@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/user/**").hasRole("USER")
			.anyRequest().authenticated().and()
			.formLogin().loginPage("/login/")
			.permitAll()
			.usernameParameter("username")
			.passwordParameter("password")
			.loginProcessingUrl("/login")
			.failureUrl("/login?error")
			.defaultSuccessUrl("/user")
			.and().logout().logoutUrl("/logout")
			.logoutSuccessUrl("/");
	}
}
