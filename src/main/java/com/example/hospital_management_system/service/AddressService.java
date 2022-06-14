package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.dto.AddressDto;
import com.example.hospital_management_system.domain.entity.Address;
import com.example.hospital_management_system.domain.mapper.AddressMapper;
import com.example.hospital_management_system.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public Optional<AddressDto> create(AddressDto addressDto) {
        Address addressToSave = addressMapper.convertToEntity(addressDto);
        if (addressRepository.existsAddressByCountryAndCityAndStreetAndBuildingAndApartment
                (addressDto.getCountry(), addressDto.getCity(), addressDto.getStreet(),
                        addressDto.getBuilding(), addressDto.getApartment())) {
            Address existingAddress = addressRepository.getAddressByCountryAndCityAndStreetAndBuildingAndApartment(
                    addressToSave.getCountry(), addressToSave.getCity(), addressToSave.getStreet(),
                    addressToSave.getBuilding(), addressToSave.getApartment());
            Address savedExistAddress = addressRepository.save(existingAddress);
            return Optional.of(addressMapper.convertToDto(savedExistAddress));
        }
        Address savedAddress = addressRepository.save(addressToSave);
        return Optional.of(addressMapper.convertToDto(savedAddress));
    }

    public Optional<AddressDto> getById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(addressMapper.convertToDto(addressOptional.get()));
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    public Optional<AddressDto> update(AddressDto addressDto, Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty()) {
            return Optional.empty();
        }
        Address addressToSave = addressMapper.convertToEntity(addressDto);
        addressOptional.get().setCountry(addressToSave.getCountry());
        addressOptional.get().setCity(addressToSave.getCity());
        addressOptional.get().setStreet(addressToSave.getStreet());
        addressOptional.get().setBuilding(addressToSave.getBuilding());
        addressOptional.get().setApartment(addressToSave.getApartment());
        Address savedAddress = addressRepository.save(addressOptional.get());
        return Optional.of(addressMapper.convertToDto(savedAddress));
    }

}
