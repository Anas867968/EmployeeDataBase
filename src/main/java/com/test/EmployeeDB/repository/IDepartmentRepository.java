package com.test.EmployeeDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.EmployeeDB.model.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {

}
