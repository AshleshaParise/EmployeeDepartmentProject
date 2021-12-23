package com.cla.employeeportaldemo.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.cla.employeeportaldemo.repository.EmployeeDepartmentRepository;
import com.cla.employeeportaldemo.repository.EmployeeRepository;

public class EmployeeDepartmentUtility 
{
	int emp_id;
	int dept_id;
	boolean result=true;
	
	@Autowired
	private EmployeeDepartmentRepository employeeDepartmentRepository;

	
	public boolean getResult(int em_id,int dp_id) 
	{
		
		return result;
	}

}
