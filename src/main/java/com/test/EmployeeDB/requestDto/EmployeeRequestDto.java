package com.test.EmployeeDB.requestDto;

import lombok.Data;

@Data
public class EmployeeRequestDto {

	private Long id;
	private String name;

	public EmployeeRequestDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public EmployeeRequestDto() {
		super();
	}

}
