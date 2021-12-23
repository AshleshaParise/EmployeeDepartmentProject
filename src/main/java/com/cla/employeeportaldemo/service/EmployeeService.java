package com.cla.employeeportaldemo.service;

import java.util.List;
import java.util.Set;

import com.cla.employeeportaldemo.dto.EmployeeDTO;
import com.cla.employeeportaldemo.entity.Employee;


public interface EmployeeService 
{
	public List<EmployeeDTO> findAll();
	
	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
	
	public EmployeeDTO updateEmployee(Integer empId, EmployeeDTO employeeDTO);
	
	public void deleteEmployee(Integer empId);

}
