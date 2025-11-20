package com.example.demo.service;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Address;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    // CREATE
    public AddressDto createAddress(AddressDto dto) {
        Address address = AddressMapper.toEntity(dto);
        Address saved = addressRepository.save(address);
        return AddressMapper.toDto(saved);
    }

    // GET ALL
    public List<AddressDto> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(AddressMapper::toDto)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public AddressDto getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        return AddressMapper.toDto(address);
    }

    // UPDATE
    public AddressDto updateAddress(Long id, AddressDto dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZip(dto.getZip());

        Address updated = addressRepository.save(address);
        return AddressMapper.toDto(updated);
    }

    // DELETE
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
