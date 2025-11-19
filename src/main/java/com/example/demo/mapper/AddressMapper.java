package com.example.demo.mapper;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Address;

public class AddressMapper {
	public static AddressDto toDto(Address a) {
		if (a == null)
			return null;
		AddressDto d = new AddressDto();
		d.setId(a.getId());
		d.setStreet(a.getStreet());
		d.setCity(a.getCity());
		d.setState(a.getState());
		d.setZip(a.getZip());
		return d;
	}

	public static Address toEntity(AddressDto d) {
		if (d == null)
			return null;
		Address a = new Address();
		a.setId(d.getId());
		a.setStreet(d.getStreet());
		a.setCity(d.getCity());
		a.setState(d.getState());
		a.setZip(d.getZip());
		return a;
	}
}