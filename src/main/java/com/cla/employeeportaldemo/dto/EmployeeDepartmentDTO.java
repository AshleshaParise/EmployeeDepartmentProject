package com.cla.employeeportaldemo.dto;

import com.cla.employeeportaldemo.entity.Department;
import com.cla.employeeportaldemo.entity.Employee;

import lombok.Data;

@Data
public class EmployeeDepartmentDTO 
{
	private Integer departmentId;
	
	private Employee employee;

	private Department department;

}
