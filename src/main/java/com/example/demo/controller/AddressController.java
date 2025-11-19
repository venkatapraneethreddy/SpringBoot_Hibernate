package com.example.demo.controller;

import com.example.demo.dto.AddressDto;
import com.example.demo.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // CREATE Address independently (rarely used in your design)
    @PostMapping
    public AddressDto createAddress(@RequestBody AddressDto dto) {
        return addressService.createAddress(dto);
    }

    // GET All Addresses
    @GetMapping
    public List<AddressDto> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // GET Address by ID
    @GetMapping("/{id}")
    public AddressDto getAddress(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    // UPDATE Address
    @PutMapping("/{id}")
    public AddressDto updateAddress(@PathVariable Long id, @RequestBody AddressDto dto) {
        return addressService.updateAddress(id, dto);
    }

    // DELETE Address
    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return "Address deleted successfully";
    }
}
