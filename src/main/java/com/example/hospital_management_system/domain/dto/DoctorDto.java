package com.example.hospital_management_system.domain.dto;

import com.example.hospital_management_system.domain.entity.User;
import com.example.hospital_management_system.domain.enums.Department;
import com.example.hospital_management_system.domain.enums.Profession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DoctorDto {
    private String name;
    private String surname;
    private Department department;
    private Profession profession;
    private User user;
}
