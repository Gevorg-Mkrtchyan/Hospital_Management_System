package com.example.hospital_management_system.controller;

import com.example.hospital_management_system.domain.dto.AddressDto;
import com.example.hospital_management_system.domain.entity.Address;
import com.example.hospital_management_system.response.EntityCreatingResponse;
import com.example.hospital_management_system.response.EntityDeletingResponse;
import com.example.hospital_management_system.response.EntityLookupResponse;
import com.example.hospital_management_system.response.EntityUpdatingResponse;
import com.example.hospital_management_system.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "hospital")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/addresses")
    @Operation(summary = "create address")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> create(@RequestBody AddressDto addressDto) {
        Optional<AddressDto> addressDtoOptional = addressService.create(addressDto);
        if (addressDtoOptional.isEmpty()) {
            return new EntityCreatingResponse<AddressDto>().onFailure("Address");
        }
        return new EntityCreatingResponse<AddressDto>().onSuccess(addressDtoOptional.get());
    }

    @GetMapping("/addresses/{id}")
    @Operation(summary = "get by address id")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<AddressDto> addressDtoOptional = addressService.getById(id);
        if (addressDtoOptional.isPresent()) {
            return new EntityLookupResponse<AddressDto>().onSuccess(addressDtoOptional.get());
        }
        return new EntityLookupResponse<AddressDto>().onFailure("Address");
    }

    @PutMapping("/addresses/{id}")
    @Operation(summary = "update by address id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> update(@RequestBody AddressDto addressDto, @PathVariable("id") Long id) {
        Optional<AddressDto> addressDtoOptional = addressService.update(addressDto, id);
        if (addressDtoOptional.isEmpty()) {
            return new EntityUpdatingResponse<Address>().onFailure("Address");
        }
        return new EntityUpdatingResponse<AddressDto>().onSuccess(addressDtoOptional.get());
    }

    @DeleteMapping("/addresses/{id}")
    @Operation(summary = "delete by address id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        Optional<AddressDto> addressDtoOptional = addressService.getById(id);
        if (addressDtoOptional.isPresent()) {
            addressService.deleteById(id);
            return new EntityDeletingResponse<AddressDto>().onSuccess(addressDtoOptional.get(), "Address");
        }
        return new EntityLookupResponse<AddressDto>().onFailure("Address");
    }
}
