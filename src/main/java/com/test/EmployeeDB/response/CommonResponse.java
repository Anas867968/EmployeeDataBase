package com.test.EmployeeDB.response;

import java.util.List;

import org.springframework.data.domain.Page;

import com.test.EmployeeDB.model.Department;
import com.test.EmployeeDB.model.Employee;

import lombok.Data;

@Data
public class CommonResponse {
	private String statusCode;
	private String statusMessage;
	private Employee update;
	private List<Employee> data;
	private Department datas;
	private List<Department> deptList;
	private Page<Employee> pagination;
	private Page<Department> deptPage;

}
