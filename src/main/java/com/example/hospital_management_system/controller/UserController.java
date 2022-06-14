package com.example.hospital_management_system.controller;


import com.example.hospital_management_system.domain.dto.UserDto;
import com.example.hospital_management_system.response.EntityCreatingResponse;
import com.example.hospital_management_system.response.EntityDeletingResponse;
import com.example.hospital_management_system.response.EntityLookupResponse;
import com.example.hospital_management_system.response.EntityUpdatingResponse;
import com.example.hospital_management_system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @Operation(summary = "create user")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> create(@RequestBody UserDto userDto){
        Optional<UserDto> userDtoOptional = userService.create(userDto);
        if (userDtoOptional.isPresent()) {
            return new EntityCreatingResponse<UserDto>().onSuccess(userDtoOptional.get());
        }
        return new EntityCreatingResponse<UserDto>().onFailure("User");
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "update by user id")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable("id") Long id) {
        Optional<UserDto> userDtoOptional = userService.update(userDto, id);
        if (userDtoOptional.isPresent()) {
            return new EntityUpdatingResponse<UserDto>().onSuccess(userDtoOptional.get());
        }
        return new EntityUpdatingResponse<UserDto>().onFailure("User");
    }

    @GetMapping("/users/get")
    @Operation(summary = "get by user email")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> getByEmail(@RequestParam("email") String email) {
        Optional<UserDto> userDtoOptional = userService.findByEmail(email);
        if (userDtoOptional.isPresent()) {
            return new EntityLookupResponse<UserDto>().onSuccess(userDtoOptional.get());
        }
        return new EntityLookupResponse<UserDto>().onFailure("User");
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "get by user id")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<UserDto> userDtoOptional = userService.getById(id);
        if (userDtoOptional.isPresent()) {
            return new EntityLookupResponse<UserDto>().onSuccess(userDtoOptional.get());
        }
        return new EntityLookupResponse<UserDto>().onFailure("User");
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "delete by user id")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> deleteByEmail(@PathVariable("id") Long id) {
        Optional<UserDto> userDtoOptional = userService.getById(id);
        if (userDtoOptional.isPresent()) {
            userService.deleteById(id);
            return new EntityDeletingResponse<UserDto>().onSuccess(userDtoOptional.get(), "User");
        }
        return new EntityLookupResponse<UserDto>().onFailure("User");
    }
}


