package com.test.EmployeeDB.model;

import java.util.Date;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "departmenttest")
@Data
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(name = "creationdate")
	private Date creationDate;
	@Column(name = "departmenthead")
	private String departmentHead;


     
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "departments")
	private List<Employee> employees;

}
