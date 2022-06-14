package com.example.hospital_management_system.service;


import com.example.hospital_management_system.domain.dto.PatientDto;
import com.example.hospital_management_system.domain.entity.Address;
import com.example.hospital_management_system.domain.entity.Patient;
import com.example.hospital_management_system.domain.mapper.PatientMapper;
import com.example.hospital_management_system.repository.AddressRepository;
import com.example.hospital_management_system.repository.PatientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;


    public PatientService(PatientMapper patientMapper, PatientRepository patientRepository,
                          AddressRepository addressRepository, PasswordEncoder passwordEncoder) {
        this.patientMapper = patientMapper;
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<PatientDto> create(PatientDto patientDto) {
        Patient patientToSave = patientMapper.convertToEntity(patientDto);
        if (addressRepository.existsAddressByCountryAndCityAndStreetAndBuildingAndApartment
                (patientToSave.getAddress().getCountry(),
                        patientToSave.getAddress().getCity(),
                        patientToSave.getAddress().getStreet(),
                        patientToSave.getAddress().getBuilding(),
                        patientToSave.getAddress().getApartment())) {
            Address existingAddress = addressRepository.getAddressByCountryAndCityAndStreetAndBuildingAndApartment(
                    patientToSave.getAddress().getCountry(),
                    patientToSave.getAddress().getCity(),
                    patientToSave.getAddress().getStreet(),
                    patientToSave.getAddress().getBuilding(),
                    patientToSave.getAddress().getApartment());
            patientToSave.setAddress(existingAddress);
        }
        patientToSave.getUser().setPassword(passwordEncoder.encode(patientToSave.getUser().getPassword()));
        Patient savedPatient = patientRepository.save(patientToSave);
        return Optional.of(patientMapper.convertToDto(savedPatient));
    }

    public Optional<PatientDto> getById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(patientMapper.convertToDto(optionalPatient.get()));
    }

    public Optional<PatientDto> getBySsn(String ssn) {
        Optional<Patient> patientOptional = patientRepository.findBySsn(ssn);
        if (patientOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(patientMapper.convertToDto(patientOptional.get()));
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public Optional<PatientDto> update(PatientDto patientDto, Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            return Optional.empty();
        }
        Patient patientToSave = patientMapper.convertToEntity(patientDto);
        patientOptional.get().setName(patientToSave.getName());
        patientOptional.get().setSurname(patientToSave.getSurname());
        patientOptional.get().setAge(patientToSave.getAge());
        patientOptional.get().setPhone(patientToSave.getPhone());
        patientOptional.get().setGender(patientToSave.getGender());
        patientOptional.get().setBloodGroup(patientToSave.getBloodGroup());
        patientOptional.get().setSsn(patientToSave.getSsn());
        patientOptional.get().getAddress().setCountry(patientToSave.getAddress().getCountry());
        patientOptional.get().getAddress().setCity(patientToSave.getAddress().getCity());
        patientOptional.get().getAddress().setStreet(patientToSave.getAddress().getStreet());
        patientOptional.get().getAddress().setBuilding(patientToSave.getAddress().getBuilding());
        patientOptional.get().getAddress().setApartment(patientToSave.getAddress().getApartment());
        patientOptional.get().getUser().setEmail(patientToSave.getUser().getEmail());
        patientOptional.get().getUser().setPassword(passwordEncoder.encode(patientToSave.getUser().getPassword()));
        patientOptional.get().getUser().setRole(patientToSave.getUser().getRole());
        patientOptional.get().getUser().setStatus(patientToSave.getUser().getStatus());
        Patient savedPatient = patientRepository.save(patientOptional.get());
        return Optional.of(patientMapper.convertToDto(savedPatient));
    }
}
