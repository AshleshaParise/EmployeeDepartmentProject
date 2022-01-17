package com.cla.employeeportaldemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cla.employeeportaldemo.service.DepartmentCollable;

@RestController
public class UserController 
{
	@Autowired
	 RestTemplate restTemplate;
	
	@Autowired
	 DepartmentCollable departmentCollable;

	
	@RequestMapping(value="/userdetails")
	public String getUserDetails() throws InterruptedException, ExecutionException
	{
		ExecutorService executor=Executors.newFixedThreadPool(10);
		List <Future<String>> userList=new ArrayList<Future<String>>();
		
		System.out.println("Start Time of Fetching User Details:  "+(System.currentTimeMillis()/1000));
		
		for(int i=0;i<10;i++) 
		{
			Future<String> future=executor.submit(departmentCollable);
			userList.add(future);
		}
		
		for(Future<String> future:userList) 
		{
			future.get();
		}
		
		executor.shutdown();
		
		System.out.println("End Time of Fetching User Details:  "+(System.currentTimeMillis()/1000));
		
		return "success";
	}
}
