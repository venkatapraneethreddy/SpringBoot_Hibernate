package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.demo.dto.ProjectDto;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public EmployeeDto create(@RequestParam Long companyId, @RequestBody EmployeeDto dto) {
		return employeeService.createEmployee(companyId, dto);
	}

	@GetMapping
	public List<EmployeeDto> all() {
		return employeeService.getAllEmployees(0, Integer.MAX_VALUE);
	}

	@GetMapping("/{id}")
	public EmployeeDto get(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@PutMapping("/{id}")
	public EmployeeDto update(@PathVariable Long id, @RequestBody EmployeeDto dto) {
		return employeeService.updateEmployee(id, dto);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return "Deleted";
	}

	@GetMapping("/{employeeId}/projects")
	public List<ProjectDto> getProjectsOfEmployee(@PathVariable Long employeeId) {
    	return employeeService.getProjectsOfEmployee(employeeId);
}

}