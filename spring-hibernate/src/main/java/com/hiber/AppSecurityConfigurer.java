/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.hiber;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.facebook.security.FacebookAuthenticationService;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.provider.SocialAuthenticationService;

import com.hiber.service.SocialAuthServiceRegistry;
import com.hiber.service.SocialConnectionSignUp;
import com.hiber.service.SocialUserDetailService;
import com.hiber.service.UserAuthProvider;

/**
 * Author : tungtt Aug 28, 2016
 */
//@EnableWebSecurity
//@Configuration
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;
	
//	private final static String API_KEY = "";
//	private final static String APP_SECRET = "";

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private UserAuthProvider userAuthProvider;

	@Autowired
	private SocialUserDetailService socialUserDetailService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
		// auth.userDetailsService(userDetailsService());
		auth.authenticationProvider(userAuthProvider);
		auth.authenticationProvider(socialAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.userDetailsService(userDetailsService());
		http.addFilterBefore(socialAuthenticationFilter(), BasicAuthenticationFilter.class).authorizeRequests()
				.antMatchers("/connect/**").permitAll();
		http.authenticationProvider(userAuthProvider);
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/user/**").hasRole("USER").anyRequest()
				.authenticated().and().formLogin().loginPage("/login/").permitAll().usernameParameter("username")
				.passwordParameter("password").loginProcessingUrl("/login").failureUrl("/login?error")
				.defaultSuccessUrl("/user").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	}

	 @Override
	 protected UserDetailsService userDetailsService() {
		 return userDetailService;
	 }

	@Bean
	public SocialAuthenticationFilter socialAuthenticationFilter() throws Exception {
		SocialAuthenticationFilter socialAuthenticationFilter = new SocialAuthenticationFilter(authenticationManager(),
				userIdSource(), inMemoryUsersConnectionRepository(), connectionFactoryLocator());
		return socialAuthenticationFilter;
	}
	
	@Bean
	public SocialAuthenticationProvider socialAuthenticationProvider() {
		return new SocialAuthenticationProvider(inMemoryUsersConnectionRepository(), socialUserDetailService);
	}

	@Bean
	public UserIdSource userIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Bean
	public InMemoryUsersConnectionRepository inMemoryUsersConnectionRepository() {
		InMemoryUsersConnectionRepository inMemoryUsersConnectionRepository = new InMemoryUsersConnectionRepository(
				connectionFactoryLocator());
		inMemoryUsersConnectionRepository.setConnectionSignUp(new SocialConnectionSignUp());
		return inMemoryUsersConnectionRepository;
	}

	@Bean
	public SocialAuthServiceRegistry connectionFactoryLocator() {
		List<SocialAuthenticationService<?>> list = new ArrayList<>();
		list.add(new FacebookAuthenticationService(env.getProperty("spring.social.facebook.app-id"), 
										env.getProperty("spring.social.facebook.app-secret")));
		return new SocialAuthServiceRegistry(list);
	}
}
