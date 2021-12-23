package com.cla.employeeportaldemo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="DEPARTMENT")
public class Department  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEPT_ID",nullable = false)
	private Integer departmentId;
	
	@Column(name="DEPT_NAME")
	private String departmentName;
	
	@Column(name="DEPT_LOCATION")
	private String departmentLocation;
	

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPT_ID")
    private List<EmployeeDepartment> employeeDepartment;


	public Department() 
	{
		
	}


	@Override
	public String toString() 
	{
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName
				+ ", departmentLocation=" + departmentLocation + ", employeeDepartment=" + employeeDepartment + "]";
	}


	public Department(Integer departmentId, String departmentName, String departmentLocation,
			List<EmployeeDepartment> employeeDepartment) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentLocation = departmentLocation;
		this.employeeDepartment = employeeDepartment;
	}
	
	public void addEmployeeDepartment(EmployeeDepartment empd){
        if(employeeDepartment == null){
        	employeeDepartment = new ArrayList<>();
        }

        employeeDepartment.add(empd);
    }


	public Department(Integer departmentId, String departmentName, String departmentLocation) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentLocation = departmentLocation;
	}
	

}
