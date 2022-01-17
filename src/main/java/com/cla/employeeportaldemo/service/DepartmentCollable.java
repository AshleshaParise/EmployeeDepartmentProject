package com.cla.employeeportaldemo.service;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DepartmentCollable implements Callable<String>
{
	@Autowired 
	RestTemplate restTemplate;
	
	@Override
	public String call() throws Exception 
	{
		return restTemplate.getForObject("http://localhost:9003/user/alluser", String.class);
	}

}
