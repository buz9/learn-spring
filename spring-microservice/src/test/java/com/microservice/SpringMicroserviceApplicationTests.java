package com.microservice;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservice.controller.UserRestServiceController;
import com.microservice.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringMicroserviceApplicationTests {

	@Autowired
	private UserRestServiceController userRestServiceController;
	
	@Test
	public void contextLoads() {
		List<User> users = userRestServiceController.listUser();
		Assert.assertTrue(users.size() > 0);
	}

}
