package com.example.demo.mapper;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Address;

public class EmployeeMapper {
	public static EmployeeDto toDto(Employee e) {
		if (e == null)
			return null;
		EmployeeDto d = new EmployeeDto();
		d.setId(e.getId());
		d.setName(e.getName());
		d.setEmail(e.getEmail());
		d.setPhone(e.getPhone());
		d.setCompanyId(e.getCompany() == null ? null : e.getCompany().getId());
		d.setAddress(AddressMapper.toDto(e.getAddress()));
		return d;
	}

	public static Employee toEntity(EmployeeDto d) {
		if (d == null)
			return null;
		Employee e = new Employee();
		e.setId(d.getId());
		e.setName(d.getName());
		e.setEmail(d.getEmail());
		e.setPhone(d.getPhone());
		if (d.getAddress() != null) {
			Address a = AddressMapper.toEntity(d.getAddress());
			e.setAddress(a);
			a.setEmployee(e);
		}
		return e;
	}
}