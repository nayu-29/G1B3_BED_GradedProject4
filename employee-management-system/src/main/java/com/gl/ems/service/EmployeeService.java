package com.gl.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.ems.entity.Employee;
import com.gl.ems.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	public Employee updateEmployee(Long id, Employee employeeDetails) {
		Employee employee = employeeRepository.findById(id).orElse(null);
		if (employee != null) {
			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			employee.setEmail(employeeDetails.getEmail());
			return employeeRepository.save(employee);
		}
		return null;
	}

	public String deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return "Deleted employee id - " + id;
	}

	public List<Employee> getEmployeesByFirstName(String firstName) {
		return employeeRepository.findByFirstName(firstName);
	}

	public List<Employee> getEmployeesSortedByFirstName(String order) {
		if (order.equalsIgnoreCase("asc")) {
			return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
		} else {
			return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
		}
	}
}
