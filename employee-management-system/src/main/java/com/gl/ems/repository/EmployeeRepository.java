package com.gl.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByFirstName(String firstName);
}
