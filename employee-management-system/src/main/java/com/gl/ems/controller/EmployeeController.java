package com.gl.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.ems.entity.Employee;
import com.gl.ems.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAPI")
	public String greet() {
		return "GET API Invoked";
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@GetMapping("/list")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		return employeeService.updateEmployee(id, employeeDetails);
	}

	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		return employeeService.deleteEmployee(id);
	}

	@GetMapping("/search/{firstName}")
	public List<Employee> getEmployeesByFirstName(@PathVariable String firstName) {
		return employeeService.getEmployeesByFirstName(firstName);
	}

	@GetMapping("/sort")
	public List<Employee> getEmployeesSortedByFirstName(@RequestParam String order) {
		return employeeService.getEmployeesSortedByFirstName(order);
	}

	@RequestMapping("/unauthUser")
	public String unauthUser() {
		return "Sorry, You do not have Access to this Page";
	}
}
