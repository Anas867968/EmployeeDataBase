package com.test.EmployeeDB.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employeetest")
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(name = "dateofbirth")
	private Date dateOfbirth;
	private double salary;
	private String department;
	private String address;
	private String role;
	@Column(name = "joiningdate")
	private Date joiningDate;
	@Column(name = "yearlybonuspercentage")
	private double yearlyBonusPercentage;
	@Column(name = "reportingmanager")
	private String reportingManager;
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	private Department departments;

}
