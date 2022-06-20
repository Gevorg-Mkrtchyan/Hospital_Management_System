package com.example.hospital_management_system.controller;

import com.example.hospital_management_system.domain.dto.DoctorDto;
import com.example.hospital_management_system.domain.entity.Doctor;
import com.example.hospital_management_system.domain.entity.WorkGraphic;
import com.example.hospital_management_system.domain.enums.Department;
import com.example.hospital_management_system.response.EntityCreatingResponse;
import com.example.hospital_management_system.response.EntityDeletingResponse;
import com.example.hospital_management_system.response.EntityLookupResponse;
import com.example.hospital_management_system.response.EntityUpdatingResponse;
import com.example.hospital_management_system.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctors")
    @Operation(summary = "doctor create",security = @SecurityRequirement(name = "hospital"))
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> create(@RequestBody DoctorDto doctorDto) {
        Optional<DoctorDto> doctorDtoOptional = doctorService.create(doctorDto);
        if (doctorDtoOptional.isEmpty()) {
            return new EntityCreatingResponse<DoctorDto>().onFailure("Doctor");
        }
        return new EntityCreatingResponse<DoctorDto>().onSuccess(doctorDtoOptional.get());
    }

    @GetMapping("/doctor/{id}")
    @Operation(summary = "get by doctor id",security = @SecurityRequirement(name = "hospital"))
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<DoctorDto> doctorDtoOptional = doctorService.getById(id);
        if (doctorDtoOptional.isPresent()) {
            return new EntityLookupResponse<DoctorDto>().onSuccess(doctorDtoOptional.get());
        }
        return new EntityLookupResponse<DoctorDto>().onFailure("Doctor");
    }

    @GetMapping("/doctors")
    @Operation(summary = "get all doctor",security = @SecurityRequirement(name = "hospital"))
    @PreAuthorize("hasAuthority('user:write')")
    public List<DoctorDto> getAll() {
        return doctorService.getDoctors();
    }

    @GetMapping("/doctors/{department}")
    @Operation(summary = "get all doctor by department")
    public List<Doctor> getAllDepartment(@PathVariable("department") Department department) {
        return doctorService.getAllByDepartment(department);
    }
    @PutMapping("doctors/{id}/{weekDay}/{start}/{end}")
    @Operation(summary = "update doctor work graphic by id",security = @SecurityRequirement(name = "hospital"))
    @PreAuthorize("hasAuthority('admin:write')")
    public void updateWorkGraphic(@PathVariable("id") Long id,
                                  @PathVariable("weekDay") DayOfWeek weekDay,
                                  @PathVariable("start") int start,
                                  @PathVariable("end") int end) {
        doctorService.updateWorkGraphic(id, weekDay, start, end);
    }
    @PostMapping("doctor/graphic/{id}")
    @Operation(summary = "add doctor work graphic by id",security = @SecurityRequirement(name = "hospital"))
    @PreAuthorize("hasAuthority('admin:write')")
    public void createWorkGraphic(@RequestBody WorkGraphic workGraphic, @PathVariable("id") Long doctorId) {
        doctorService.addDoctorWorkGraphic(workGraphic,doctorId);
    }
    @PutMapping("/doctors/{id}")
    @Operation(summary = "update by doctor id",security = @SecurityRequirement(name = "hospital"))
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> update(@RequestBody DoctorDto doctorDto, @PathVariable("id") Long id) {
        Optional<DoctorDto> doctorDtoOptional = doctorService.update(doctorDto, id);
        if (doctorDtoOptional.isEmpty()) {
            return new EntityUpdatingResponse<Doctor>().onFailure("Doctor");
        }
        return new EntityUpdatingResponse<DoctorDto>().onSuccess(doctorDtoOptional.get());
    }

    @DeleteMapping("/doctors/{id}")
    @Operation(summary = "delete by doctor id",security = @SecurityRequirement(name = "hospital"))
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        Optional<DoctorDto> doctorDtoOptional = doctorService.getById(id);
        if (doctorDtoOptional.isPresent()) {
            doctorService.deleteById(id);
            return new EntityDeletingResponse<DoctorDto>().onSuccess(doctorDtoOptional.get(), "Doctor");
        }
        return new EntityLookupResponse<DoctorDto>().onFailure("Doctor");
    }
}
