package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Address;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.AddressRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.ProjectDto;
import com.example.demo.mapper.ProjectMapper;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	private final CompanyRepository companyRepository;
	private final AddressRepository addressRepository;

	public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository,
			AddressRepository addressRepository) {
		this.employeeRepository = employeeRepository;
		this.companyRepository = companyRepository;
		this.addressRepository = addressRepository;
	}

public EmployeeDto createEmployee(Long companyId, EmployeeDto dto) {
	Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("Company not found"));
			Employee e = EmployeeMapper.toEntity(dto);
			e.setCompany(company);
			if (e.getAddress() != null) {
			e.getAddress().setEmployee(e);
			}
			Employee saved = employeeRepository.save(e);
			return EmployeeMapper.toDto(saved);
			}

	public List<EmployeeDto> getAllEmployees(int page, int size) {
			// simplified: not using Page here; you can expand to PageRequest and return Page
			return
			employeeRepository.findAll().stream().map(EmployeeMapper::toDto).collect(Collectors.toList());
			}

	public EmployeeDto getEmployeeById(Long id) {
		Employee e = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
		return EmployeeMapper.toDto(e);
	}

	public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
		Employee e = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
		e.setName(dto.getName());
		e.setEmail(dto.getEmail());
		e.setPhone(dto.getPhone());
		if (dto.getAddress() != null) {
			Address a = e.getAddress();
			if (a == null) {
				a = AddressMapper.toEntity(dto.getAddress());
				a.setEmployee(e);
				e.setAddress(a);
			} else {
				a.setStreet(dto.getAddress().getStreet());
				a.setCity(dto.getAddress().getCity());
				a.setState(dto.getAddress().getState());
				a.setZip(dto.getAddress().getZip());
			}
		}
		Employee saved = employeeRepository.save(e);
		return EmployeeMapper.toDto(saved);
	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	public List<ProjectDto> getProjectsOfEmployee(Long employeeId) {
    	Employee employee = employeeRepository.findById(employeeId)
            	.orElseThrow(() -> new RuntimeException("Employee not found"));

    	return employee.getProjects()
                   .stream()
                   .map(ProjectMapper::toDto)
                   .toList();
}

}
