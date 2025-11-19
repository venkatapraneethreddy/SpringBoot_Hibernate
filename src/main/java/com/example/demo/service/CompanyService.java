package com.example.demo.service;

import com.example.demo.dto.CompanyDto;
import com.example.demo.entity.Company;
import com.example.demo.mapper.CompanyMapper;
import com.example.demo.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public CompanyDto createCompany(CompanyDto dto) {
		Company c = CompanyMapper.toEntity(dto);
		Company saved = companyRepository.save(c);
		return CompanyMapper.toDto(saved);
	}

	public CompanyDto getCompanyById(Long id) {
		Company c = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
		return CompanyMapper.toDto(c);
	}

	public CompanyDto getCompanyByName(String name) {
		Company c = companyRepository.findByName(name).orElseThrow(() -> new RuntimeException("Company not found"));
		return CompanyMapper.toDto(c);
	}

	public List<CompanyDto> getAll() {
		return companyRepository.findAll().stream().map(CompanyMapper::toDto).collect(Collectors.toList());
	}

	public CompanyDto updateCompany(Long id, CompanyDto dto) {
		Company c = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
		c.setName(dto.getName());
		c.setLocation(dto.getLocation());
		Company updated = companyRepository.save(c);
		return CompanyMapper.toDto(updated);
	}

	public void deleteCompany(Long id) {
		companyRepository.deleteById(id);
	}
}