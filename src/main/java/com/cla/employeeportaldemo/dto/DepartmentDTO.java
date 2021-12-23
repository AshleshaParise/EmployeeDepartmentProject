package com.cla.employeeportaldemo.dto;

import java.io.Serializable;
import java.util.List;

import com.cla.employeeportaldemo.entity.Employee;
import com.cla.employeeportaldemo.entity.EmployeeDepartment;

import lombok.Data;

@Data
public class DepartmentDTO implements Serializable
{
	private Integer departmentId;

	private String departmentName;

	private String departmentLocation;
	
    private List<Employee> employee;

	
}
