package com.cla.employeeportaldemo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.cla.employeeportaldemo.dto.DepartmentDTO;
import com.cla.employeeportaldemo.dto.EmployeeDTO;
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
	 
	 private static final Logger logger = LogManager.getLogger(EmployeeController.class);
	 
	 @GetMapping("/department")
	 public List<DepartmentDTO> findAllDepartment() 
	 {
	        return departmentService.findAll();
	 }
	 
	 
	 @PostMapping("/department")
	 public ResponseEntity<DepartmentDTO> addEmployee(@RequestBody DepartmentDTO departmentDTO )
	 {
		 DepartmentDTO deptDto=departmentService.addDepartment(departmentDTO);
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
