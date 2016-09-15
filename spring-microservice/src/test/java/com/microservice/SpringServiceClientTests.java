/***************************************************************************
 * 							tungtt							               *    
 **************************************************************************/
package com.microservice;

import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.microservice.model.Group;
import com.microservice.model.User;

import junit.framework.Assert;

/**
 *  Author : tungtt         
 * Sep 15, 2016
 */
@RunWith(SpringRunner.class)
public class SpringServiceClientTests {
	private RestTemplate restTemplate;
	
	@Before
	public void setUp() {
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient httpClient = builder.build();
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
	}
	
	@Test
	public void contextLoads() {
		String url = "http://localhost:8080/list/users";
		ResponseEntity<List> entity = restTemplate.getForEntity(url, List.class);
		List<User> users = (List<User>) entity.getBody();
		
		url = "http://localhost:8080/list/groups";
		ResponseEntity<Group[]> groupEntity = restTemplate.getForEntity(url, Group[].class);
		Group[] groups = groupEntity.getBody();
		Assert.assertTrue(groups.length > 0);
		
		for(int i = 0; i < groups.length; i++) {
			System.out.println(groups[i].getId());
		}
		
		url = "http://localhost:8080/get/user/test3";
		User user = new User();
		user.setUsername("test3");
		user.setPassword("123456");
		user.setAge(25);
		user.setGroupId(101);
		user.setEmail("test@gmail.com");
		
		ResponseEntity<String> insertEntity = restTemplate.postForEntity(url, user, String.class);
		Assert.assertEquals(user.getUsername(), insertEntity.getBody());
		
		url = "http://localhost:8080/delete/user/test3";
		ResponseEntity<Void> delEntity = restTemplate.getForEntity(url, Void.class);
		Assert.assertEquals(null, delEntity.getBody());
	}
}
