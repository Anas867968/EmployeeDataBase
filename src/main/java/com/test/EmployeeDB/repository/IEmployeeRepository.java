package com.test.EmployeeDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.test.EmployeeDB.model.Employee;

import jakarta.transaction.Transactional;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
	@Transactional
	@Modifying
	@Query(value = "update employeetest set department=:department where id=:id", nativeQuery = true)
	public int updateDepartment(@Param("department") String department, @Param("id") Long id);

	public List<Employee> findAllByName(String name);

	public List<Employee> findByDepartmentsId(Long departmentId);

}
