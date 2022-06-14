package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.dto.DoctorDto;
import com.example.hospital_management_system.domain.entity.Doctor;
import com.example.hospital_management_system.domain.enums.Department;
import com.example.hospital_management_system.domain.mapper.DoctorMapper;
import com.example.hospital_management_system.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorMapper doctorMapper, DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.doctorMapper = doctorMapper;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<DoctorDto> create(DoctorDto doctorDto) {
        Doctor doctorToSave = doctorMapper.convertToEntity(doctorDto);
        doctorToSave.getUser().setPassword(passwordEncoder.encode(doctorToSave.getUser().getPassword()));
        Doctor savedDoctor = doctorRepository.save(doctorToSave);
        return Optional.of(doctorMapper.convertToDto(savedDoctor));
    }

    public Optional<DoctorDto> getById(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(doctorMapper.convertToDto(doctorOptional.get()));
    }

    public List<DoctorDto> getDoctors() {
        return doctorMapper.mapAllToDoctorDto(doctorRepository.findAll());
    }

    public List<Doctor> getAllByDepartment(Department department) {
        return doctorRepository.findAllByDepartment(department);
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    public Optional<DoctorDto> update(DoctorDto doctorDto, Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            return Optional.empty();
        }
        Doctor doctorToSave = doctorMapper.convertToEntity(doctorDto);
        doctorOptional.get().setName(doctorToSave.getName());
        doctorOptional.get().setSurname(doctorToSave.getSurname());
        doctorOptional.get().setDepartment(doctorToSave.getDepartment());
        doctorOptional.get().setProfession(doctorToSave.getProfession());
        doctorOptional.get().getUser().setEmail(doctorToSave.getUser().getEmail());
        doctorOptional.get().getUser().setPassword(passwordEncoder.encode(doctorToSave.getUser().getPassword()));
        doctorOptional.get().getUser().setRole(doctorToSave.getUser().getRole());
        doctorOptional.get().getUser().setStatus(doctorToSave.getUser().getStatus());
        Doctor savedDoctor = doctorRepository.save(doctorOptional.get());
        return Optional.of(doctorMapper.convertToDto(savedDoctor));
    }
}

