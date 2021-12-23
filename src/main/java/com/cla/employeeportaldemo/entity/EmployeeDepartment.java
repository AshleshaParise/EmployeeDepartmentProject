package com.cla.employeeportaldemo.entity;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="EMPLOYEE_DEPARTMENT")
public class EmployeeDepartment implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ED_ID",nullable = false)
	private Integer departmentId;
	
	 @ManyToOne(fetch = FetchType.EAGER, optional = false)
	 @JoinColumn(name = "EMP_ID",referencedColumnName = "EMP_ID")
	 @Fetch(FetchMode.JOIN)
	 @JsonIgnore
	 private Employee employee;

	 @ManyToOne(fetch = FetchType.EAGER,optional = false)
	 @JoinColumn(name = "DEPT_ID",referencedColumnName = "DEPT_ID")
	 @Fetch(FetchMode.JOIN)
	 @JsonIgnore
	 private Department department;
		
	 public EmployeeDepartment() 
	 {
		  
	 }

	
	
		 	
}
