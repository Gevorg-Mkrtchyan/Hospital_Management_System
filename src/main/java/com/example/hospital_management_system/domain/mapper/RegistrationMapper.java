package com.example.hospital_management_system.domain.mapper;

import com.example.hospital_management_system.domain.dto.RegistrationDto;
import com.example.hospital_management_system.domain.entity.Registration;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper implements BaseMapper<Registration, RegistrationDto>{
    @Override
    public Registration convertToEntity(RegistrationDto dto) {
        Registration registration = new Registration();
        registration.setTime(dto.getTime());
        registration.setRegDay(dto.getRegDay());
        return registration;
    }

    @Override
    public RegistrationDto convertToDto(Registration entity) {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setTime(entity.getTime());
        registrationDto.setRegDay(entity.getRegDay());
        return registrationDto;
    }
}
