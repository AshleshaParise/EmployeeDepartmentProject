package com.cla.employeeportaldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cla.employeeportaldemo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> 
{
	public Employee findByEmployeeId(Integer employeeId);
	public Employee deleteByEmployeeId(Integer employeeId);


}
