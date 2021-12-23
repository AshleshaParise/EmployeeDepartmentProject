package com.cla.employeeportaldemo.dto;

import java.io.Serializable;
import java.util.List;

import com.cla.employeeportaldemo.entity.Department;
import com.cla.employeeportaldemo.entity.EmployeeDepartment;

import lombok.Data;

@Data
public class EmployeeDTO implements Serializable
{
	private Integer employeeId;
	
	private String employeeName;
	
	private Integer employeePhone;

	private String employeeAddress;

	private String employeeDesignation;

	private Integer employeeSalary;

    private List<Department> department;



}
