package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping
	public ProjectDto create(@RequestParam Long companyId, @RequestBody ProjectDto dto) {
		return projectService.createProject(companyId, dto);
	}
	
	@PostMapping("/{projectId}/employees/{employeeId}")
	public EmployeeDto assignEmployee(
	        @PathVariable Long projectId,
	        @PathVariable Long employeeId) {

	    return projectService.assignEmployeeToProject(projectId, employeeId);
	}

	@DeleteMapping("/{projectId}/employees/{employeeId}")
	public EmployeeDto removeEmployee(
	        @PathVariable Long projectId,
	        @PathVariable Long employeeId) {

	    return projectService.removeEmployeeFromProject(projectId, employeeId);
	}


	@GetMapping
	public List<ProjectDto> byCompany(@RequestParam(required = false) Long companyId) {
		if (companyId != null)
			return projectService.getProjectsByCompany(companyId);
		return projectService.getProjectsByCompany(null);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		projectService.deleteProject(id);
		return "Deleted";
	}

	@GetMapping("/{projectId}/employees")
	public List<EmployeeDto> getEmployeesInProject(@PathVariable Long projectId) {
    	return projectService.getEmployeesByProject(projectId);
}

}