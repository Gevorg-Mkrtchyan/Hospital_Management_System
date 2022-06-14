package com.example.hospital_management_system.domain.mapper;

import com.example.hospital_management_system.domain.dto.AddressDto;
import com.example.hospital_management_system.domain.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements BaseMapper<Address, AddressDto>{
    @Override
    public Address convertToEntity(AddressDto dto) {
        Address address = new Address();
        address.setCountry(dto.getCountry());
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setBuilding(dto.getBuilding());
        address.setApartment(dto.getApartment());
        return address;
    }

    @Override
    public AddressDto convertToDto(Address entity) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCountry(entity.getCountry());
        addressDto.setCity(entity.getCity());
        addressDto.setStreet(entity.getStreet());
        addressDto.setBuilding(entity.getBuilding());
        addressDto.setApartment(entity.getApartment());
        return addressDto;
    }
}
