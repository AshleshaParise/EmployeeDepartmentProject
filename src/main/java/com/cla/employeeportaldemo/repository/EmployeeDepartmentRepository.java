package com.cla.employeeportaldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cla.employeeportaldemo.entity.Employee;
import com.cla.employeeportaldemo.entity.EmployeeDepartment;

@Repository
public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment,Integer> 
{

}
