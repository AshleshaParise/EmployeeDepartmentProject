package com.cla.employeeportaldemo.service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cla.employeeportaldemo.entity.Employee;
import com.cla.employeeportaldemo.repository.EmployeeRepository;

@Component
public class EmployeeCollable implements Callable<Employee>
{
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Transactional
	@Override
	public Employee call() throws Exception 
	{
		List<Employee> employeeList = employeeRepository.findAll();
		Random random = new Random();
		return employeeList.get(random.nextInt(employeeList.size()));
	}
}
