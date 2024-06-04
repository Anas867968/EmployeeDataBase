package com.test.EmployeeDB.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.test.EmployeeDB.model.Employee;
import com.test.EmployeeDB.repository.IEmployeeRepository;

import com.test.EmployeeDB.response.CommonResponse;
import com.test.EmployeeDB.service.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository dao;

	@Override
	public CommonResponse add(List<Employee> obj) {
		List<Employee> emp = dao.saveAll(obj);
		CommonResponse response = new CommonResponse();
		if (emp.size() > 0) {
			response.setStatusMessage("Employee Added");
			response.setStatusCode("200");
			response.setData(emp);

		} else {
			response.setStatusMessage("Employee not found");
			response.setStatusCode("404");

		}
		return response;
	}

	@Override
	public Employee update(Employee e) {
		try {

			Optional<Employee> existingEmployee = dao.findById(e.getId());

			if (existingEmployee.isPresent()) {
				Employee employeeToUpdate = existingEmployee.get();
				employeeToUpdate.setName(e.getName());
				employeeToUpdate.setDateOfbirth(e.getDateOfbirth());
				employeeToUpdate.setSalary(e.getSalary());
				employeeToUpdate.setDepartment(e.getDepartment());
				employeeToUpdate.setAddress(e.getAddress());
				employeeToUpdate.setRole(e.getRole());
				employeeToUpdate.setJoiningDate(e.getJoiningDate());
				employeeToUpdate.setYearlyBonusPercentage(e.getYearlyBonusPercentage());
				employeeToUpdate.setReportingManager(e.getReportingManager());

				return dao.save(employeeToUpdate);
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	@Override
	public int updateDepartment(String dept, Long id) {

		return dao.updateDepartment(dept, id);
	}

	@Override
	public CommonResponse searchAll(int page, int pagesize) {
		try {
			Page<Employee> employees = dao.findAll(PageRequest.of(page, pagesize));
			CommonResponse response = new CommonResponse();
			if (employees.getSize() > 0) {
				response.setStatusMessage("Employees List Displayed");
				response.setStatusCode("200");
				response.setPagination(employees);

			} else {
				response.setStatusMessage("Not found");
				response.setStatusCode("404");

			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Page<Employee> getAllEmployees(int page, int pagesize) {

		return dao.findAll(PageRequest.of(page, pagesize));
	}

}
