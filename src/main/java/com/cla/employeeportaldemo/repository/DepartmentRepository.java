package com.cla.employeeportaldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cla.employeeportaldemo.entity.Department;
import com.cla.employeeportaldemo.entity.Employee;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> 
{
	public Department findByDepartmentId(Integer departmentId);
}
