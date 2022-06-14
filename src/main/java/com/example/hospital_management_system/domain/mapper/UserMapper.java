package com.example.hospital_management_system.domain.mapper;

import com.example.hospital_management_system.domain.dto.UserDto;
import com.example.hospital_management_system.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<User, UserDto>{
    @Override
    public User convertToEntity(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        return user;
    }

    @Override
    public UserDto convertToDto(User entity) {
        UserDto dto = new UserDto();
//        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
