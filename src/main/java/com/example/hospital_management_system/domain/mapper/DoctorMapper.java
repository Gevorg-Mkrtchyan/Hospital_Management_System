package com.example.hospital_management_system.domain.mapper;

import com.example.hospital_management_system.domain.dto.DoctorDto;
import com.example.hospital_management_system.domain.entity.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper implements BaseMapper<Doctor, DoctorDto>{
    public Doctor convertToEntity(DoctorDto dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSurname(dto.getSurname());
        doctor.setDepartment(dto.getDepartment());
        doctor.setProfession(dto.getProfession());
        doctor.setPhone(dto.getPhone());
        doctor.setUser(dto.getUser());
        return doctor;
    }

    @Override
    public DoctorDto convertToDto(Doctor entity) {
        DoctorDto dto = new DoctorDto();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setDepartment(entity.getDepartment());
        dto.setProfession(entity.getProfession());
        dto.setPhone(entity.getPhone());
        dto.setUser(entity.getUser());
        return dto;
    }
    public List<DoctorDto> mapAllToDoctorDto(List<Doctor> doctors){
        return doctors.stream().map(this::convertToDto).collect(Collectors.toList());

    }
}
