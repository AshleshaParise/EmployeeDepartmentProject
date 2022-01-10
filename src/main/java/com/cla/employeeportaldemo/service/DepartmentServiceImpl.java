package com.cla.employeeportaldemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cla.employeeportaldemo.controller.EmployeeController;
import com.cla.employeeportaldemo.dto.DepartmentDTO;
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
public class DepartmentServiceImpl implements DepartmentService 
{	
	 @Autowired
	 private EmployeeRepository employeeRepository;
	 
	 @Autowired
	 private DepartmentRepository departmentRepository;
	
	 @Autowired
	 private EmployeeDepartmentRepository employeeDepartmentRepository;
	 
	 private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);
	
	 
	@Transactional
	@Override
	public List<DepartmentDTO> findAll() 
	{
		List<DepartmentDTO> departmentDTO = new ArrayList<>();
		List<Department> department = departmentRepository.findAll();
		logger.info("Fetching Department details aalong with Department details");
		logger.info("Fetching Department details:  "+Thread.currentThread().getName());
		department.stream().forEach(dept -> {
			DepartmentDTO deptdto = mapEntityToDto(dept);
			departmentDTO.add(deptdto);
		});
		return departmentDTO;
	}
	
	@Transactional
	@Override
	public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
		 Department department = new Department();
		 logger.info("Running Thread from Department Service: "+Thread.currentThread().getName());
		 mapDtoToEntity(departmentDTO, department);
		 Department savedDepartment = departmentRepository.save(department);
		 logger.info("Adding Department details into the database");
		 return mapEntityToDto(savedDepartment);
	}
	
	@Transactional
	@Override
	public DepartmentDTO updateDepartment(Integer deptId, DepartmentDTO departmentDTO) 
	{
		 Department department = departmentRepository.findByDepartmentId(deptId);
		 if(department==null) {
				throw new IDNotFoundException("Department id not found  "+ deptId);
			}
		 Department dept = departmentRepository.save(mapDtoToEntityUpdate(departmentDTO, department));
		 logger.info("Updating Department with Department Id: "+deptId);
		 return mapEntityToDto(dept);
	}

	@Transactional
	@Override
	public void deleteDepartment(Integer deptId) 
	{
		Department department=departmentRepository.findByDepartmentId(deptId);
		if(department==null) {
			throw new IDNotFoundException("Department id not found  "+ deptId);
		}
		EmployeeDepartment emdt=employeeDepartmentRepository.getById(deptId);
		employeeDepartmentRepository.delete(emdt);
		logger.info("Deleting Department of Department Id: "+deptId);
		departmentRepository.delete(department); 
	}
	
	@Transactional
	@Async(value="taskExecutor")
	@Override
	public DepartmentDTO findDepartment(Integer departmentId) throws InterruptedException
	{	
		  
		  Department department=departmentRepository.findByDepartmentId(departmentId);
		 
	      if(department==null) 
	      {
				throw new IDNotFoundException("Department id not found  "+ departmentId);
		  }
	      Thread.sleep(5000);
	      DepartmentDTO deptdto = mapEntityToDto(department);
	      logger.info("Fetching data from database");
	      logger.info("Thread Name of Department Service:  "+Thread.currentThread().getName());

		  return deptdto;
	}

	
	private DepartmentDTO mapEntityToDto(Department savedDepartment) 
	  { 
		 DepartmentDTO deptdto = new DepartmentDTO(); 
		 EmployeeDepartmentDTO empdeptdto=new EmployeeDepartmentDTO(); 
		 deptdto.setDepartmentId(savedDepartment.getDepartmentId());
		 deptdto.setDepartmentName(savedDepartment.getDepartmentName());
		 deptdto.setDepartmentLocation(savedDepartment.getDepartmentLocation());
	  
		 deptdto.setEmployee(savedDepartment.getEmployeeDepartment().stream().map(ed->ed.getEmployee()).collect(Collectors.toList()));
		 return deptdto;
	  }
	
	 private Department mapDtoToEntity(DepartmentDTO departmentDTO, Department department) 
	  {
		 department.setDepartmentId(departmentDTO.getDepartmentId());
			//employee.setEmployeeId(employeeDTO.getEmployeeId());
		 department.setDepartmentName(departmentDTO.getDepartmentName());
		 department.setDepartmentLocation(departmentDTO.getDepartmentLocation());
			
			
			EmployeeDepartment empDept = new EmployeeDepartment();

			
			  List<Employee> emp=departmentDTO.getEmployee();
			  
			  for(Employee e:emp) {
			  
			  empDept.setDepartment(department); 
			  empDept.setEmployee(e);
			  department.addEmployeeDepartment(empDept); 
			  }
			 
			return department;
			
	  }
	 private Department mapDtoToEntityUpdate(DepartmentDTO departmentDTO, Department department) 
	  {
		 department.setDepartmentId(departmentDTO.getDepartmentId());
			//employee.setEmployeeId(employeeDTO.getEmployeeId());
		 department.setDepartmentName(departmentDTO.getDepartmentName());
		 department.setDepartmentLocation(departmentDTO.getDepartmentLocation());
			
			
			EmployeeDepartment empDept = new EmployeeDepartment();

			
			  List<Employee> emp=departmentDTO.getEmployee();
			  
			  for(Employee e:emp) {
			  
			  empDept.setDepartment(department); 
			  empDept.setEmployee(e);
			  //department.addEmployeeDepartment(empDept); 
			  }
			 
			return department;
			
	  }

}
