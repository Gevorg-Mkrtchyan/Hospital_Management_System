package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.dto.UserDto;
import com.example.hospital_management_system.domain.entity.User;
import com.example.hospital_management_system.domain.mapper.UserMapper;
import com.example.hospital_management_system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDto> create(UserDto userDto){
        User userToSave = userMapper.convertToEntity(userDto);
        userToSave.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(userToSave);
        return Optional.of(userMapper.convertToDto(savedUser));
    }

    public Optional<UserDto> update(UserDto userDto, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            return Optional.empty();
        }
        User userToSave = userMapper.convertToEntity(userDto);
//        userOptional.get().setEmail(userToSave.getEmail());
        userOptional.get().setPassword(passwordEncoder.encode(userToSave.getPassword()));
        userOptional.get().setRole(userToSave.getRole());
        userOptional.get().setStatus(userToSave.getStatus());
        User savedUser = userRepository.save(userOptional.get());
        return Optional.of(userMapper.convertToDto(savedUser));
    }

    public Optional<UserDto> getById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.convertToDto(userOptional.get()));
    }
    public Optional<UserDto> findByEmail(String email){
        Optional<User> userOptional=  userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(userMapper.convertToDto(userOptional.get()));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}