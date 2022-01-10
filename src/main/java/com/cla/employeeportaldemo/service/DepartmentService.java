package com.cla.employeeportaldemo.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.cla.employeeportaldemo.dto.DepartmentDTO;
import com.cla.employeeportaldemo.dto.EmployeeDTO;

public interface DepartmentService 
{
	public  List<DepartmentDTO> findAll();
	
	public DepartmentDTO addDepartment(DepartmentDTO departmentDTO);
	
	public DepartmentDTO updateDepartment(Integer deptId, DepartmentDTO departmentDTO);
	
	public void deleteDepartment(Integer deptId);

	public DepartmentDTO findDepartment(Integer departmentId) throws InterruptedException;

}
