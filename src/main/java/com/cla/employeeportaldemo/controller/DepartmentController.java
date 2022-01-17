package com.cla.employeeportaldemo.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.client.RestTemplate;

import com.cla.employeeportaldemo.dto.DepartmentDTO;
import com.cla.employeeportaldemo.dto.EmployeeDTO;
import com.cla.employeeportaldemo.exception.AsyncExceptionHandler;
import com.cla.employeeportaldemo.repository.DepartmentRepository;
import com.cla.employeeportaldemo.repository.EmployeeRepository;
import com.cla.employeeportaldemo.service.DepartmentService;
import com.cla.employeeportaldemo.service.EmployeeService;

@RestController
@RequestMapping(value="/api")
public class DepartmentController 
{
	 @Autowired
	 private DepartmentService departmentService;
	
	 @Autowired
	 EmployeeRepository employeeRepository;
	 
	 @Autowired
	 private DepartmentRepository departmentRepository;
	 
	 @Autowired
	 private AsyncExceptionHandler asyncExceptionHandler;
	 
	 @Autowired
	 RestTemplate restTemplate;
	 
	 private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	 
	 @GetMapping("/department")
	 public List<DepartmentDTO> findAllDepartment() 
	 {
	        return departmentService.findAll();
	 }
	 
	 @GetMapping("/department/{departmentId}")
	 public ResponseEntity<String>  findEmployeeDetails(@PathVariable(name="departmentId") Integer departmentId) throws InterruptedException
	 {
		    System.out.println("Department Controller Thread:  "+Thread.currentThread().getName());
		    if( departmentRepository.findByDepartmentId(departmentId)!= null) {
		    	DepartmentDTO departmentdto=departmentService.findDepartment(departmentId);
		    	return new ResponseEntity<>("Id found",HttpStatus.OK);
		    }
		    else 
		    	return new ResponseEntity<>("Id not found",HttpStatus.BAD_REQUEST);
		    
			
	 } 
	 
	 
	 
	 @PostMapping("/department")
	 public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO )
	 {
		    DepartmentDTO deptDto=departmentService.addDepartment(departmentDTO);
			System.out.println("Running Thread addDepartment  "+Thread.currentThread().getName());
			return new ResponseEntity<>(deptDto,HttpStatus.CREATED);
			
	 } 
	 
	 @PutMapping("/department/{departmentId}")
	 public ResponseEntity<DepartmentDTO> updateDepartmentDetails(@PathVariable(name="departmentId") Integer departmentId,
				@RequestBody DepartmentDTO departmentdto )
	 {
		    DepartmentDTO deptDto=departmentService.updateDepartment(departmentId,departmentdto);
			return new ResponseEntity<>(deptDto,HttpStatus.CREATED);
	 } 
	 
	 @DeleteMapping("/department/{departmentId}")
	 public ResponseEntity<String> deleteDepartment(@PathVariable(name="departmentId") Integer departmentId)
	 {
			departmentService.deleteDepartment(departmentId);
			return new ResponseEntity<>("Deleted",HttpStatus.OK);
	 } 
	 
}
