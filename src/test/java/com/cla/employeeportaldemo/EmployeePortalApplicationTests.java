package com.cla.employeeportaldemo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cla.employeeportaldemo.dto.DepartmentDTO;
import com.cla.employeeportaldemo.entity.Department;
import com.cla.employeeportaldemo.repository.DepartmentRepository;
import com.cla.employeeportaldemo.service.DepartmentServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= {EmployeePortalApplicationTests.class})
class EmployeePortalApplicationTests {


	@InjectMocks
	private DepartmentServiceImpl departmentServiceImpl;
	
	@Mock
	private DepartmentRepository departmentRepository;

	@Test
		@Order(1)
		public void getDepartmenTest() 
		{
			
			
		}
	
	

 
		
}
