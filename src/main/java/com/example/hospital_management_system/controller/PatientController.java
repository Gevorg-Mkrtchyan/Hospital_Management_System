package com.example.hospital_management_system.controller;

import com.example.hospital_management_system.domain.dto.PatientDto;
import com.example.hospital_management_system.response.EntityCreatingResponse;
import com.example.hospital_management_system.response.EntityDeletingResponse;
import com.example.hospital_management_system.response.EntityLookupResponse;
import com.example.hospital_management_system.response.EntityUpdatingResponse;
import com.example.hospital_management_system.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "hospital")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patient")
    @Operation(summary = "create patient")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> create(@RequestBody PatientDto patientDto) {
        Optional<PatientDto> patientDtoOptional = patientService.create(patientDto);
        if (patientDtoOptional.isEmpty()) {
            return new EntityCreatingResponse<PatientDto>().onFailure("Patient");
        }
        return new EntityCreatingResponse<PatientDto>().onSuccess(patientDtoOptional.get());
    }

    @GetMapping("/patient/{id}")
    @Operation(summary = "get patient by id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<PatientDto> patientDtoOptional = patientService.getById(id);
        if (patientDtoOptional.isPresent()) {
            return new EntityLookupResponse<PatientDto>().onSuccess(patientDtoOptional.get());
        }
        return new EntityLookupResponse<PatientDto>().onFailure("Patient");
    }

    @GetMapping("/patient")
    @Operation(summary = "get patient by ssn")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> getBySsn(@RequestParam("ssn") String ssn) {
        Optional<PatientDto> patientDtoOptional = patientService.getBySsn(ssn);
        if (patientDtoOptional.isPresent()) {
            return new EntityLookupResponse<PatientDto>().onSuccess(patientDtoOptional.get());
        }
        return new EntityLookupResponse<PatientDto>().onFailure("Patient");
    }

    @PutMapping("/patient/{id}")
    @Operation(summary = "update patient by id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> update(@RequestBody PatientDto patientDto, @PathVariable("id") Long id) {
        Optional<PatientDto> patientDtoOptional = patientService.update(patientDto, id);
        if (patientDtoOptional.isEmpty()) {
            return new EntityUpdatingResponse<PatientDto>().onFailure("Patient");
        }
        return new EntityUpdatingResponse<PatientDto>().onSuccess(patientDtoOptional.get());
    }

    @DeleteMapping("/patient/{id}")
    @Operation(summary = "delete patient by id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        Optional<PatientDto> patientDtoOptional = patientService.getById(id);
        if (patientDtoOptional.isPresent()) {
            patientService.deleteById(id);
            return new EntityDeletingResponse<PatientDto>().onSuccess(patientDtoOptional.get(), "Patient");
        }
        return new EntityLookupResponse<PatientDto>().onFailure("Patient");
    }
}


