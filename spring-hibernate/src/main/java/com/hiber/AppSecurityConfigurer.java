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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialAuthenticationFilter;

import com.hiber.service.UserAuthProvider;

/**
 * Author : tungtt Aug 28, 2016
 */
@EnableWebSecurity
@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private UserDetailsService detailsService;

	@Autowired
	private UserAuthProvider userAuthProvider;
	
	@Autowired
	private SocialAuthenticationFilter socialAuthencationFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
//		auth.userDetailsService(userDetailsService());
		auth.authenticationProvider(userAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/user/**").hasRole("USER").anyRequest()
//				.authenticated().and().formLogin().loginPage("/login/").permitAll().usernameParameter("username")
//				.passwordParameter("password").loginProcessingUrl("/login").failureUrl("/login?error")
//				.defaultSuccessUrl("/user").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	
//		http.userDetailsService(userDetailsService()).authorizeRequests().antMatchers("/").permitAll().antMatchers("/user/**").hasRole("USER").anyRequest()
//		.authenticated().and().formLogin().loginPage("/login/").permitAll().usernameParameter("username")
//		.passwordParameter("password").loginProcessingUrl("/login").failureUrl("/login?error")
//		.defaultSuccessUrl("/user").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
		
		http.authenticationProvider(userAuthProvider).addFilter(socialAuthencationFilter).authorizeRequests().antMatchers("/").permitAll().antMatchers("/user/**").hasRole("USER").anyRequest()
		.authenticated().and().formLogin().loginPage("/login/").permitAll().usernameParameter("username")
		.passwordParameter("password").loginProcessingUrl("/login").failureUrl("/login?error")
		.defaultSuccessUrl("/user").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	}

//	@Override
//	protected UserDetailsService userDetailsService() {
//	    return detailsService;
//	}
	
}
