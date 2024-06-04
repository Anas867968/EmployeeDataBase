package com.test.EmployeeDB.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.test.EmployeeDB.model.Department;
import com.test.EmployeeDB.model.Employee;
import com.test.EmployeeDB.repository.IDepartmentRepository;
import com.test.EmployeeDB.repository.IEmployeeRepository;
import com.test.EmployeeDB.requestDto.DepartmentRequestDto;
import com.test.EmployeeDB.response.CommonResponse;
import com.test.EmployeeDB.service.IDepartmentService;



@Service
public class DepartmentService implements IDepartmentService {
	@Autowired
	private IDepartmentRepository dao;
	@Autowired
	private IEmployeeRepository Edao;

	@Override
	public CommonResponse add(Department dept) {
		Department emp = dao.save(dept);
		CommonResponse response = new CommonResponse();
		if (emp != null) {
			response.setStatusMessage("Department Added");
			response.setStatusCode("200");
			response.setDatas(emp);

		} else {
			response.setStatusMessage("Department not found");
			response.setStatusCode("404");

		}
		return response;
	}

	@Override
	public Department update(Department d) {
		Optional<Department> dept = dao.findById(d.getId());
		if (dept.isPresent()) {
			Department department = dept.get();
			department.setName(d.getName());
			department.setCreationDate(d.getCreationDate());
			department.setDepartmentHead(d.getDepartmentHead());
			return dao.save(department);
		} else {
			return null;
		}

	}

//	@Override
//	public CommonResponse displayAll(int page, int pagesize) {
//		try {
//
//			Page<Department> departments = dao.findAll(PageRequest.of(page, pagesize));
//			CommonResponse response = new CommonResponse();
//			if (departments.getSize() > 0) {
//				response.setStatusCode("200");
//				response.setStatusMessage("List of Departments Displayed");
//				response.setDeptPage(departments);
//			} else {
//				response.setStatusCode("404");
//				response.setStatusMessage("Not Found");
//			}
//			return response;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public CommonResponse deleteDepartment(Long departmentId) {
		CommonResponse response = new CommonResponse();

		Optional<Department> departmentOptional = dao.findById(departmentId);
		if (!departmentOptional.isPresent()) {
			response.setStatusCode("404");
			response.setStatusMessage("Department not found");
			return response;
		}

		List<Employee> employees = Edao.findByDepartmentsId(departmentId);
		if (!employees.isEmpty()) {
			response.setStatusCode("400");
			response.setStatusMessage("Cannot delete department with assigned employees");
			return response;
		}

		dao.deleteById(departmentId);
		response.setStatusCode("200");
		response.setStatusMessage("Department deleted successfully");
		return response;
	}

	@Override
	public Page<Department> getAllEmployeesInDept(int page, int pagesize, String expand) {
		if (expand.equalsIgnoreCase("employee")) {
			return dao.findAll(PageRequest.of(page, pagesize));
		} else {
			return null;
		}
	}

	@Override
	public List<DepartmentRequestDto> getAllDept(int page, int pagesize) {
		            List<Department> departments=dao.findAll();
		            List<DepartmentRequestDto> dtos=new ArrayList<DepartmentRequestDto>();
		            for(Department department: departments) {
		            	DepartmentRequestDto dto=new DepartmentRequestDto();
		            	BeanUtils.copyProperties(department, dto);
		            	dtos.add(dto);
		            }
		return dtos;
	}

}
