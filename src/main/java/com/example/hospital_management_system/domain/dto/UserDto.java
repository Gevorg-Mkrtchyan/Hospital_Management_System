package com.example.hospital_management_system.domain.dto;

import com.example.hospital_management_system.domain.enums.Role;
import com.example.hospital_management_system.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private String email;
    private String password;
    private Status status;
    private Role role;
}
