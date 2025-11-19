package com.example.demo.controller;

import com.example.demo.dto.CompanyDto;
import com.example.demo.service.CompanyService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@PostMapping
	public CompanyDto create(@RequestBody CompanyDto dto) {
		return companyService.createCompany(dto);
	}

	@GetMapping
	public List<CompanyDto> all() {
		return companyService.getAll();
	}

	@GetMapping("/{id}")
	public CompanyDto getById(@PathVariable Long id) {
		return companyService.getCompanyById(id);
	}

	@GetMapping("/by-name")
	public CompanyDto byName(@RequestParam String name) {
		return companyService.getCompanyByName(name);
	}

	@PutMapping("/{id}")
	public CompanyDto update(@PathVariable Long id, @RequestBody CompanyDto dto) {
		return companyService.updateCompany(id, dto);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		companyService.deleteCompany(id);
		return "Deleted";
	}
}