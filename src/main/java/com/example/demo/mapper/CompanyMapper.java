package com.example.demo.mapper;

import com.example.demo.dto.CompanyDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.ProjectDto;
import com.example.demo.entity.Company;
import java.util.stream.Collectors;

public class CompanyMapper {
	public static CompanyDto toDto(Company company) {
		if (company == null)
			return null;
		CompanyDto d = new CompanyDto();
		d.setId(company.getId());
		d.setName(company.getName());
		d.setLocation(company.getLocation());
		d.setProjects(company.getProjects() == null ? null : company.getProjects().stream().map(p -> {
			ProjectDto pd = new ProjectDto();
			pd.setId(p.getId());
			pd.setTitle(p.getTitle());
			pd.setCompanyId(p.getCompany() == null ? null : p.getCompany().getId());
			pd.setEmployeeIds(p.getEmployees() == null ? null
					: p.getEmployees().stream().map(e -> e.getId()).collect(java.util.stream.Collectors.toSet()));
			return pd;
		}).collect(Collectors.toList()));
		d.setEmployees(company.getEmployees() == null ? null : company.getEmployees().stream().map(e -> {
			EmployeeDto ed = new EmployeeDto();
			ed.setId(e.getId());
			ed.setName(e.getName());
			ed.setEmail(e.getEmail());
			ed.setPhone(e.getPhone());
			ed.setCompanyId(e.getCompany() == null ? null : e.getCompany().getId());
			return ed;
		}).collect(Collectors.toList()));
		return d;
	}

	public static Company toEntity(CompanyDto d) {
		if (d == null)
			return null;
		Company c = new Company();
		c.setId(d.getId());
		c.setName(d.getName());
		c.setLocation(d.getLocation());
		return c;
	}
}