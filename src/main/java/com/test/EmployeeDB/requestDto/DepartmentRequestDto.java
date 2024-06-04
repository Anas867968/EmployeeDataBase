package com.test.EmployeeDB.requestDto;

import java.util.Date;

import com.test.EmployeeDB.model.Department;

import lombok.Data;
@Data
public class DepartmentRequestDto {
	private Long id;
    private String name;
    private Date creationDate;
    private String departmentHead;
    
    public DepartmentRequestDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.creationDate = department.getCreationDate();
        this.departmentHead = department.getDepartmentHead();
    }

	public DepartmentRequestDto() {
		// TODO Auto-generated constructor stub
	}

}
