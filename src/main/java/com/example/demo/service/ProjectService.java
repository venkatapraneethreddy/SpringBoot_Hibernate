package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository,
                          CompanyRepository companyRepository,
                          EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    // ------------------ EXISTING METHODS ------------------

    public ProjectDto createProject(Long companyId, ProjectDto dto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Project p = ProjectMapper.toEntity(dto);
        p.setCompany(company);

        if (dto.getEmployeeIds() != null) {
            for (Long empId : dto.getEmployeeIds()) {
                Employee e = employeeRepository.findById(empId)
                        .orElseThrow(() -> new RuntimeException("Employee not found"));

                p.getEmployees().add(e);
                e.getProjects().add(p);
            }
        }

        Project saved = projectRepository.save(p);
        return ProjectMapper.toDto(saved);
    }

    public EmployeeDto assignEmployeeToProject(Long projectId, Long employeeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        project.getEmployees().add(employee);
        employee.getProjects().add(project);

        projectRepository.save(project);
        employeeRepository.save(employee);

        return EmployeeMapper.toDto(employee);
    }

    public EmployeeDto removeEmployeeFromProject(Long projectId, Long employeeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        project.getEmployees().remove(employee);
        employee.getProjects().remove(project);

        projectRepository.save(project);
        employeeRepository.save(employee);

        return EmployeeMapper.toDto(employee);
    }

    public List<ProjectDto> getProjectsByCompany(Long companyId) {
        return projectRepository.findByCompanyId(companyId)
                .stream()
                .map(ProjectMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

   

    public Page<EmployeeDto> getEmployeesByProject(Long projectId, int page, int size, String sort) {

        // sorting logic
        Sort sortOrder;
        if (sort != null && sort.contains(",")) {
            String[] parts = sort.split(",");
            sortOrder = parts[1].equalsIgnoreCase("desc") ?
                    Sort.by(parts[0]).descending() :
                    Sort.by(parts[0]).ascending();
        } else {
            sortOrder = Sort.by("id").ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sortOrder);

        Page<Employee> employeePage =
                employeeRepository.findByProjects_Id(projectId, pageable);

        return employeePage.map(EmployeeMapper::toDto);
    }
}
