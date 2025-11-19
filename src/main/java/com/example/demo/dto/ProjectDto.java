package com.example.demo.dto;

import java.util.Set;

public class ProjectDto {
	private Long id;
	private String title;
	private Long companyId;
	private Set<Long> employeeIds;

// getters/setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Set<Long> getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(Set<Long> employeeIds) {
		this.employeeIds = employeeIds;
	}
}
