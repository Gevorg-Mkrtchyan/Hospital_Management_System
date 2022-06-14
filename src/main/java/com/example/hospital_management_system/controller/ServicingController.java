package com.example.hospital_management_system.controller;

import com.example.hospital_management_system.domain.dto.ServicingDto;
import com.example.hospital_management_system.domain.entity.Servicing;
import com.example.hospital_management_system.response.EntityCreatingResponse;
import com.example.hospital_management_system.response.EntityDeletingResponse;
import com.example.hospital_management_system.response.EntityLookupResponse;
import com.example.hospital_management_system.response.EntityUpdatingResponse;
import com.example.hospital_management_system.service.ServicingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ServicingController {
    private final ServicingService servicingService;

    public ServicingController(ServicingService servicingService) {
        this.servicingService = servicingService;
    }

    @PostMapping("/servicing")
    @Operation(summary = "create servicing")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> create(@RequestBody ServicingDto servicingDto) {
        Optional<ServicingDto> servicingDtoOptional = servicingService.create(servicingDto);
        if (servicingDtoOptional.isPresent()) {
            return new EntityCreatingResponse<ServicingDto>().onSuccess(servicingDtoOptional.get());
        }
        return new EntityCreatingResponse<ServicingDto>().onFailure("Servicing");
    }

    @GetMapping("/servicing/{id}")
    @Operation(summary = "get by servicing id")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<ServicingDto> servicingDtoOptional = servicingService.getById(id);
        if (servicingDtoOptional.isPresent()) {
            return new EntityLookupResponse<ServicingDto>().onSuccess(servicingDtoOptional.get());
        }
        return new EntityLookupResponse<ServicingDto>().onFailure("Servicing");
    }

    @GetMapping("/servicing")
    @Operation(summary = "get all servicing")
    @PreAuthorize("hasAuthority('user:write')")
    public List<Servicing> getAll() {
        return servicingService.getAll();
    }

    @PutMapping("/sevicing/{id}")
    @Operation(summary = "update by servicing id")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> update(@RequestBody ServicingDto servicingDto, @PathVariable("id") Long id) {
        Optional<ServicingDto> servicingDtoOptional = servicingService.update(servicingDto, id);
        if (servicingDtoOptional.isEmpty()) {
            return new EntityUpdatingResponse<Servicing>().onFailure("Servicing");
        }
        return new EntityUpdatingResponse<ServicingDto>().onSuccess(servicingDtoOptional.get());
    }

    @DeleteMapping("/servicing{id}")
    @Operation(summary = "delete by servicing id")
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        Optional<ServicingDto> servicingDtoOptional = servicingService.getById(id);
        if (servicingDtoOptional.isPresent()) {
            servicingService.deleteByID(id);
            return new EntityDeletingResponse<ServicingDto>().onSuccess(servicingDtoOptional.get(),"Servicing");
        }
        return new EntityLookupResponse<ServicingDto>().onFailure("Servicing");
    }
}

