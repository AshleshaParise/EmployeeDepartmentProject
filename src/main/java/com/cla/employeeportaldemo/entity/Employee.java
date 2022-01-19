package com.cla.employeeportaldemo.entity;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EMP_ID",nullable = false)
	private Integer employeeId;
	
	@Column(name="EMP_NAME")
	private String employeeName;
	
	@Column(name="EMP_PHONE")
	private Integer employeePhone;
	
	@Column(name="EMP_ADDRESS")
	private String employeeAddress;
	
	@Column(name="EMP_DESIGNATION")
	private String employeeDesignation;
	
	@Column(name="EMP_SALARY")
	private Integer employeeSalary;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMP_ID")
    private List<EmployeeDepartment> employeeDepartment;

	
	public Employee() 
	{

	}
	


	@Override
	public String toString() 
	{
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeePhone="
				+ employeePhone + ", employeeAddress=" + employeeAddress + ", employeeDesignation="
				+ employeeDesignation + ", employeeSalary=" + employeeSalary;
	}
	
	



	public Employee(Integer employeeId, String employeeName, Integer employeePhone, String employeeAddress,
			String employeeDesignation, Integer employeeSalary, List<EmployeeDepartment> employeeDepartment) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeePhone = employeePhone;
		this.employeeAddress = employeeAddress;
		this.employeeDesignation = employeeDesignation;
		this.employeeSalary = employeeSalary;
		this.employeeDepartment = employeeDepartment;
	}
	
	public void addEmployeeDepartment(EmployeeDepartment empDepartment)
	{
        if(employeeDepartment == null){
        	employeeDepartment = new ArrayList<>();
        }

        employeeDepartment.add(empDepartment);
     }
	

	
	
	
	public void removeDepartment(EmployeeDepartment empdepartment) 
	{
		this.getEmployeeDepartment().remove(empdepartment);
	}
	
	public void removeDepartments() 
	{
		for(EmployeeDepartment empdept:new ArrayList<>(employeeDepartment)) {
			removeDepartment(empdept);
		}
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeAddress == null) {
			if (other.employeeAddress != null)
				return false;
		} else if (!employeeAddress.equals(other.employeeAddress))
			return false;
		if (employeeDepartment == null) {
			if (other.employeeDepartment != null)
				return false;
		} else if (!employeeDepartment.equals(other.employeeDepartment))
			return false;
		if (employeeDesignation == null) {
			if (other.employeeDesignation != null)
				return false;
		} else if (!employeeDesignation.equals(other.employeeDesignation))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (employeePhone == null) {
			if (other.employeePhone != null)
				return false;
		} else if (!employeePhone.equals(other.employeePhone))
			return false;
		if (employeeSalary == null) {
			if (other.employeeSalary != null)
				return false;
		} else if (!employeeSalary.equals(other.employeeSalary))
			return false;
		return true;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeAddress == null) ? 0 : employeeAddress.hashCode());
		result = prime * result + ((employeeDepartment == null) ? 0 : employeeDepartment.hashCode());
		result = prime * result + ((employeeDesignation == null) ? 0 : employeeDesignation.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + ((employeePhone == null) ? 0 : employeePhone.hashCode());
		result = prime * result + ((employeeSalary == null) ? 0 : employeeSalary.hashCode());
		return result;
	}
	
}