package com.cla.employeeportaldemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cla.employeeportaldemo.entity.Employee;
import com.cla.employeeportaldemo.repository.EmployeeRepository;
import com.cla.employeeportaldemo.service.DepartmentCollable;
import com.cla.employeeportaldemo.service.EmployeeCollable;

@RestController
public class UserController 
{
	@Autowired
	 RestTemplate restTemplate;
	
	@Autowired
	 DepartmentCollable departmentCollable;
	
	@Autowired
	 EmployeeCollable employeeCallable;
	
	@Autowired
	 EmployeeRepository employeeRepository;
     static int count=1;

	
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
			System.out.println(future.get());

		}
		
		executor.shutdown();
		
		System.out.println("End Time of Fetching User Details:  "+(System.currentTimeMillis()/1000));
		
		return "success";
	}
	
	//mapping for updating address of employee
	@RequestMapping(value="/employeeupdate")
	public String employeeUpdateAddress() throws InterruptedException, ExecutionException
	{
		ExecutorService executor=Executors.newFixedThreadPool(10);
		List <Future<Employee>> employeeList=new ArrayList<Future<Employee>>();
		List <Future<Employee>> employeeList1=new ArrayList<Future<Employee>>();

		//take employee randomly from employee table
		List<Employee> employee = employeeRepository.findAll();
		for(int i=0;i<employee.size();i++) 
		{
			Future<Employee> future=executor.submit(employeeCallable);
			employeeList.add(future);
		}
		
		//Update address of employee
		for(Future<Employee> future:employeeList) 
		{
			Employee emp=future.get();
			Runnable runnableTask = () -> {
			    String address=emp.getEmployeeAddress();
				emp.setEmployeeAddress(address+count);
				count++;
				System.out.println("Thread Name: "+Thread.currentThread().getName()); 
			};
			Future<Employee> future1=(Future<Employee>) executor.submit(runnableTask);
			employeeList1.add(future1);
		}
		for(Future<Employee> future:employeeList) 
		{
			Employee emp=future.get();
			System.out.println("Updated Employee Address  "+emp);
		}
		executor.shutdown();	
		return "Address updated successfully";
	}
	
}

