package com.test.EmployeeDB.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.test.EmployeeDB.model.Employee;
import com.test.EmployeeDB.response.CommonResponse;

public interface IEmployeeService {

	public CommonResponse add(List<Employee> obj);

	public Employee update(Employee e);

	public int updateDepartment(String dept, Long id);

	public CommonResponse searchAll(int page, int pagesize);

	public Page<Employee> getAllEmployees(int page, int pagesize);

}
