package com.test.EmployeeDB.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.test.EmployeeDB.model.Department;
import com.test.EmployeeDB.requestDto.DepartmentRequestDto;
import com.test.EmployeeDB.response.CommonResponse;

public interface IDepartmentService {

	public CommonResponse add(Department dept);

	public Department update(Department d);

//	public CommonResponse displayAll(int page, int pagesize);

	public CommonResponse deleteDepartment(Long departmentId);

	public Page<Department> getAllEmployeesInDept(int page, int pagesize, String expand);
	
	public List<DepartmentRequestDto> getAllDept(int page,int pagesize);
}
