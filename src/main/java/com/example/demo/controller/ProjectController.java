package com.example.demo.controller;

import java.util.List;

<<<<<<< HEAD
import org.springframework.data.domain.Page;
=======
>>>>>>> e6be921d50ff3e445bc43a1919d947bdf03c51d4
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
<<<<<<< HEAD

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // ---------------- CREATE PROJECT ----------------
    @PostMapping
    public ProjectDto create(
            @RequestParam Long companyId,
            @RequestBody ProjectDto dto) {

        return projectService.createProject(companyId, dto);
    }

    // ---------------- ASSIGN EMPLOYEE ----------------
    @PostMapping("/{projectId}/employees/{employeeId}")
    public EmployeeDto assignEmployee(
            @PathVariable Long projectId,
            @PathVariable Long employeeId) {

        return projectService.assignEmployeeToProject(projectId, employeeId);
    }

    // ---------------- REMOVE EMPLOYEE ----------------
    @DeleteMapping("/{projectId}/employees/{employeeId}")
    public EmployeeDto removeEmployee(
            @PathVariable Long projectId,
            @PathVariable Long employeeId) {

        return projectService.removeEmployeeFromProject(projectId, employeeId);
    }

    // ---------------- GET PROJECTS BY COMPANY ----------------
    @GetMapping
    public List<ProjectDto> byCompany(@RequestParam(required = false) Long companyId) {

        if (companyId != null)
            return projectService.getProjectsByCompany(companyId);

        return List.of(); // empty list instead of passing null
    }

    // ---------------- DELETE PROJECT ----------------
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "Project deleted successfully";
    }

    // ---------------- PAGINATION + SORTING ----------------
    @GetMapping("/{projectId}/employees")
    public Page<EmployeeDto> getEmployeesInProject(
            @PathVariable Long projectId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {

        return projectService.getEmployeesByProject(projectId, page, size, sort);
    }
}
=======
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
>>>>>>> e6be921d50ff3e445bc43a1919d947bdf03c51d4
