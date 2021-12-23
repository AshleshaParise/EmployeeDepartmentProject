package com.cla.employeeportaldemo.controller;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cla.employeeportaldemo.dto.EmployeeDTO;
import com.cla.employeeportaldemo.entity.Employee;
import com.cla.employeeportaldemo.repository.EmployeeRepository;
import com.cla.employeeportaldemo.service.EmployeeService;



@RestController
@RequestMapping(value="/api")
public class EmployeeController 
{
	 @Autowired
	 private EmployeeService employeeService;
	 
	 @Autowired
	 EmployeeRepository employeeRepository;
	 
	 private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	 
	 @Autowired
	 public EmployeeController(EmployeeService employeeService)
	 {
	       this.employeeService = employeeService;
	 }
	 
	 
	 @GetMapping("/employee")
	 public List<EmployeeDTO> findAllEmployees() 
	 {	
		    logger.info("Getting All Employee");
	        return employeeService.findAll();
	 }
	 
	 @PostMapping("/employee")
	 public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO )
	 {
			EmployeeDTO empDto=employeeService.addEmployee(employeeDTO);
		    logger.info("Adding employee in database");
			return new ResponseEntity<>(empDto,HttpStatus.CREATED);
			
	 } 
	 
	 @PutMapping("/employee/{employeeId}")
	 public ResponseEntity<EmployeeDTO> updateEmployeeDetails(@PathVariable(name="employeeId") Integer employeeId,
				@RequestBody EmployeeDTO employeedto )
	 {
			EmployeeDTO empDto=employeeService.updateEmployee(employeeId,employeedto);
		    logger.info("Update employee details of ID:  "+employeeId);
			return new ResponseEntity<>(empDto,HttpStatus.CREATED);
	 } 
	 
	 @DeleteMapping("/employee/{employeeId}")
	 public ResponseEntity<String> deleteEmployee(@PathVariable(name="employeeId") Integer employeeId)
		{
			employeeService.deleteEmployee(employeeId);
		    logger.info("Delete employee details of ID:  "+employeeId);
			return new ResponseEntity<>("Deleted",HttpStatus.OK);
		} 

}
