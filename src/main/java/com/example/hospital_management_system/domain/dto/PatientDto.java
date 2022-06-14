package com.example.hospital_management_system.domain.dto;

import com.example.hospital_management_system.domain.entity.Address;
import com.example.hospital_management_system.domain.entity.User;
import com.example.hospital_management_system.domain.enums.BloodGroup;
import com.example.hospital_management_system.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PatientDto {
    private String name;
    private String surname;
    private Long age;
    private Gender gender;
    private BloodGroup bloodGroup;
    private String phone;
    private String ssn;
    private Address address;
    private User user;
}
