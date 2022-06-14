package com.example.hospital_management_system.domain.mapper;

import com.example.hospital_management_system.domain.dto.PatientDto;
import com.example.hospital_management_system.domain.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper implements BaseMapper<Patient, PatientDto>{
    @Override
    public Patient convertToEntity(PatientDto dto) {
        Patient patient = new Patient();
        patient.setAge(dto.getAge());
        patient.setName(dto.getName());
        patient.setSurname(dto.getSurname());
        patient.setGender(dto.getGender());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setPhone(dto.getPhone());
        patient.setSsn(dto.getSsn());
        patient.setUser(dto.getUser());
        patient.setAddress(dto.getAddress());
        return patient;
    }


    @Override
    public PatientDto convertToDto(Patient entity) {
        PatientDto patientDto = new PatientDto();
        patientDto.setAge(entity.getAge());
        patientDto.setName(entity.getName());
        patientDto.setSurname(entity.getSurname());
        patientDto.setGender(entity.getGender());
        patientDto.setBloodGroup(entity.getBloodGroup());
        patientDto.setPhone(entity.getPhone());
        patientDto.setSsn(entity.getSsn());
        patientDto.setUser(entity.getUser());
        patientDto.setAddress(entity.getAddress());
        return patientDto;
    }
}
