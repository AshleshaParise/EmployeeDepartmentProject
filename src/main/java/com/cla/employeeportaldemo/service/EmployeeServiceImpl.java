package com.cla.employeeportaldemo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cla.employeeportaldemo.dto.EmployeeDTO;
import com.cla.employeeportaldemo.dto.EmployeeDepartmentDTO;
import com.cla.employeeportaldemo.entity.Department;
import com.cla.employeeportaldemo.entity.Employee;
import com.cla.employeeportaldemo.entity.EmployeeDepartment;
import com.cla.employeeportaldemo.exception.IDNotFoundException;
import com.cla.employeeportaldemo.repository.DepartmentRepository;
import com.cla.employeeportaldemo.repository.EmployeeDepartmentRepository;
import com.cla.employeeportaldemo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService 
{
	
	 @Autowired
	 private EmployeeRepository employeeRepository;
	 
	 @Autowired
	 private DepartmentRepository departmentRepository;
	 @Autowired
	 private EmployeeDepartmentRepository employeeDepartmentRepository;
	 
	 @Override
	// @Cacheable(value = "employeeCache",key="#EmployeeDTO.employeeId", condition="#employeeId!=null")
	 public List<EmployeeDTO> findAll() 
	 {
		List<EmployeeDTO> employeeDTO = new ArrayList<>();
		List<Employee> employee = employeeRepository.findAll();
		employee.stream().forEach(emp -> {
			EmployeeDTO empdto = mapEntityToDto(emp);
			employeeDTO.add(empdto);
		});
		System.out.println("Fetching data from db");
		return employeeDTO;
	 }
	 
	 @Transactional
	 @Override
	 @Cacheable(value = "employeeCache",key="#EmployeeDTO.employeeId", condition="#employeeId!=null")
	 public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) 
	 {	
		 Employee employee = new Employee();
		 mapDtoToEntity(employeeDTO, employee);
		 Employee savedEmployee = employeeRepository.save(employee);
		 return mapEntityToDto(savedEmployee);
	 }
	 
	 	 
	 @Transactional
	 @Override
	 @CachePut(cacheNames ="employeeCache",key="#empId")
	 public EmployeeDTO updateEmployee(Integer empId, EmployeeDTO employeeDTO)
	 {
		 Employee employee = employeeRepository.findByEmployeeId(empId);
		 if(employee==null) {
				throw new IDNotFoundException("Employee id not found  "+ empId);
			}
		 Employee emp = employeeRepository.save(mapDtoToEntityUpdate(employeeDTO, employee));
		 System.out.println("Fetching data from database");
		 return mapEntityToDto(emp);
	 }
	 
	 @Transactional
	 @Override
	 @CacheEvict(cacheNames ="employeeCache",key="#empId")
	 public void deleteEmployee(Integer empId)
	 {
		    
		      Employee employee=employeeRepository.findByEmployeeId(empId);	
		      if(employee==null) {
					throw new IDNotFoundException("Employee id not found  "+ empId);
				}
			  EmployeeDepartment emdt=employeeDepartmentRepository.getById(empId);
			  employeeDepartmentRepository.delete(emdt);
			  employeeRepository.delete(employee);			
			 
	 }
	 
	 @Override
	 @Cacheable(cacheNames = "employeeCache",key="#employeeId")
	 public EmployeeDTO findEmployee(Integer employeeId) 
	 {
		  
	      Employee employee=employeeRepository.findByEmployeeId(employeeId);	
	      if(employee==null) 
	      {
				throw new IDNotFoundException("Employee id not found  "+ employeeId);
		  }
	      EmployeeDTO empdto = mapEntityToDto(employee);
		  System.out.println("Fetching data from database");

		  return empdto;
	 }
	 
		  
	
	  private Employee mapDtoToEntity(EmployeeDTO employeeDTO, Employee employee) 
	  {
		    employee.setEmployeeName(employeeDTO.getEmployeeName());
			//employee.setEmployeeId(employeeDTO.getEmployeeId());
			employee.setEmployeePhone(employeeDTO.getEmployeePhone());
			employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());
			employee.setEmployeeDesignation(employeeDTO.getEmployeeDesignation());
			employee.setEmployeeSalary(employeeDTO.getEmployeeSalary());
			
			EmployeeDepartment empDept = new EmployeeDepartment();

			
			  List<Department> dept=employeeDTO.getDepartment();
			  
			  for(Department d:dept) {
			  
			  empDept.setEmployee(employee); 
			  empDept.setDepartment(d);
			  employee.addEmployeeDepartment(empDept); 
			  //employeeDepartmentRepository.save(empDept);
			  }
			 
			return employee;
			
	  }
	  private Employee mapDtoToEntityUpdate(EmployeeDTO employeeDTO, Employee employee) 
	  {
		    employee.setEmployeeName(employeeDTO.getEmployeeName());
			employee.setEmployeeId(employeeDTO.getEmployeeId());
			employee.setEmployeePhone(employeeDTO.getEmployeePhone());
			employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());
			employee.setEmployeeDesignation(employeeDTO.getEmployeeDesignation());
			employee.setEmployeeSalary(employeeDTO.getEmployeeSalary());
			
			EmployeeDepartment empDept = new EmployeeDepartment();

			
			  List<Department> dept=employeeDTO.getDepartment();
			  
			  for(Department d:dept) {
			  
			  empDept.setEmployee(employee); 
			  empDept.setDepartment(d);
			  //employee.addEmployeeDepartment(empDept); 
			  //employeeDepartmentRepository.save(empDept);
			  }
			 
			return employee;
			
	  }
	  
	  
	  
	 
	  private EmployeeDTO mapEntityToDto(Employee employee) 
	  { 
	     EmployeeDTO empdto = new EmployeeDTO();  
		 empdto.setEmployeeName(employee.getEmployeeName());
		 empdto.setEmployeeId(employee.getEmployeeId());
		 empdto.setEmployeePhone(employee.getEmployeePhone());
		 empdto.setEmployeeAddress(employee.getEmployeeAddress());
		 empdto.setEmployeeDesignation(employee.getEmployeeDesignation());
		 empdto.setEmployeeSalary(employee.getEmployeeSalary());
	  
		 empdto.setDepartment(employee.getEmployeeDepartment().stream().map(ed->ed.getDepartment()).collect(Collectors.toList()));
		 return empdto;
	  }

	
	 
}
