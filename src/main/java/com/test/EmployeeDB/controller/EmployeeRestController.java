package com.test.EmployeeDB.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.EmployeeDB.model.Department;
import com.test.EmployeeDB.model.Employee;
import com.test.EmployeeDB.requestDto.DepartmentRequestDto;
import com.test.EmployeeDB.requestDto.EmployeeRequestDto;
import com.test.EmployeeDB.response.CommonResponse;
import com.test.EmployeeDB.service.IDepartmentService;
import com.test.EmployeeDB.service.IEmployeeService;

@RestController
public class EmployeeRestController {
	@Autowired
	private IEmployeeService service;
	@Autowired
	private IDepartmentService service1;

	@PostMapping("/save-employee")
	public CommonResponse createEmployee(@RequestBody List<Employee> data) {

		return service.add(data);
	}

	@PostMapping("/save-dept")
	public CommonResponse createDepartment(@RequestBody Department data) {

		return service1.add(data);
	}

	@PutMapping("/update-employee")
	public Employee updateEmp(@RequestBody Employee emp) {

		return service.update(emp);
	}

	@PutMapping("/update-department")
	public Department updateEmp(@RequestBody Department dept) {

		return service1.update(dept);
	}

	@PutMapping("/{id}/department")
	public String updateDepartment(@PathVariable Long id, @RequestParam("department") String department) {
		int rowsUpdated = service.updateDepartment(department, id);
		if (rowsUpdated > 0) {
			return "Department updated successfully";
		} else {
			return "Failed to update department";
		}

	}

	@GetMapping("/list-Employees/{page}/{pagesize}")
	public CommonResponse display(@PathVariable int page, @PathVariable int pagesize) {
		return service.searchAll(page, pagesize);
	}

	@GetMapping("/list-Departments/{page}/{pagesize}")
	public List<DepartmentRequestDto> findAllDepts(@PathVariable int page, @PathVariable int pagesize) {
		return service1.getAllDept(page, pagesize);
	}
	
	

	@GetMapping("/employees/{page}/{pagesize}")
	public List<EmployeeRequestDto> getEmployees(@RequestParam(value = "lookup", defaultValue = "false") boolean lookup,
			@PathVariable int page, @PathVariable int pagesize) {
		if (lookup) {
			List<EmployeeRequestDto> employeeRequestDtos = new ArrayList<>();
			Page<Employee> employees = service.getAllEmployees(page, pagesize);
			for (Employee employee : employees) {
				EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto();
				employeeRequestDto.setId(employee.getId());
				employeeRequestDto.setName(employee.getName());
				employeeRequestDtos.add(employeeRequestDto);
			}
			return employeeRequestDtos;
		} else {
			return new ArrayList<>();
		}
	}

	@DeleteMapping("/departments/{departmentId}")
	public CommonResponse deleteDepartment(@PathVariable Long departmentId) {
		return service1.deleteDepartment(departmentId);
	}

	@GetMapping("/Employees-In-Department/{page}/{pagesize}")
	public CommonResponse getAllEmployeeInADepartment(@PathVariable int page, @PathVariable int pagesize,
			@RequestParam("expand") String expand) {
		CommonResponse response = new CommonResponse();
		Page<Department> departments = service1.getAllEmployeesInDept(page, pagesize, expand);
		if (departments != null) {
			response.setStatusMessage("List of Departments found");
			response.setStatusCode("200");
			response.setDeptPage(departments);
		} else {
			response.setStatusMessage("List not Found");
			response.setStatusCode("404");
		}
		return response;
	}

}
