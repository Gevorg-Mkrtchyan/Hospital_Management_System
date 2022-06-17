package com.example.hospital_management_system.domain.mapper;

import com.example.hospital_management_system.domain.dto.DoctorAppointmentDto;
import com.example.hospital_management_system.domain.entity.DoctorAppointment;
import org.springframework.stereotype.Component;

@Component
public class DoctorAppointmentMapper implements BaseMapper<DoctorAppointment, DoctorAppointmentDto> {
    @Override
    public DoctorAppointment convertToEntity(DoctorAppointmentDto dto) {
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        doctorAppointment.setDate(dto.getDate());
        doctorAppointment.setEndTime(dto.getEndTime());
        doctorAppointment.setStartTime(dto.getStartTime());
        doctorAppointment.setAppointmentStatus(dto.getAppointmentStatus());
        return doctorAppointment;
    }

    @Override
    public DoctorAppointmentDto convertToDto(DoctorAppointment entity) {
        DoctorAppointmentDto dto = new DoctorAppointmentDto();
        dto.setDate(entity.getDate());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getStartTime());
        dto.setAppointmentStatus(entity.getAppointmentStatus());
        return dto;
    }
}
