package com.example.demo.mapper;

import com.example.demo.dto.ProjectDto;
import com.example.demo.entity.Project;
import com.example.demo.entity.Employee;
import java.util.HashSet;
import java.util.Set;

public class ProjectMapper {
	public static ProjectDto toDto(Project p) {
		if (p == null)
			return null;
		ProjectDto d = new ProjectDto();
		d.setId(p.getId());
		d.setTitle(p.getTitle());
		d.setCompanyId(p.getCompany() == null ? null : p.getCompany().getId());
		if (p.getEmployees() != null) {
			Set<Long> ids = new HashSet<>();
			for (Employee e : p.getEmployees())
				ids.add(e.getId());
			d.setEmployeeIds(ids);
		}
		return d;
	}

	public static Project toEntity(ProjectDto d) {
		if (d == null)
			return null;
		Project p = new Project();
		p.setId(d.getId());
		p.setTitle(d.getTitle());
		return p;
	}
}