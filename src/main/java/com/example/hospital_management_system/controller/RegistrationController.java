package com.example.hospital_management_system.controller;

import com.example.hospital_management_system.domain.dto.RegistrationDto;
import com.example.hospital_management_system.domain.entity.DoctorAppointment;
import com.example.hospital_management_system.response.EntityCreatingResponse;
import com.example.hospital_management_system.response.EntityDeletingResponse;
import com.example.hospital_management_system.response.EntityLookupResponse;
import com.example.hospital_management_system.response.EntityUpdatingResponse;
import com.example.hospital_management_system.service.DoctorAppointmentService;
import com.example.hospital_management_system.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final DoctorAppointmentService doctorAppointmentService;

    public RegistrationController(RegistrationService registrationService, DoctorAppointmentService doctorAppointmentService) {
        this.registrationService = registrationService;
        this.doctorAppointmentService = doctorAppointmentService;
    }

    @PostMapping("/registrations/{doctor_id}/{patient_id}/{servicing_id}")
    @Operation(summary = "create registration by doctor_id,patient_id,servicing_id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> create(@RequestBody RegistrationDto registrationDto,
                                    @PathVariable("doctor_id") Long doctorId,
                                    @PathVariable("patient_id") Long patientId,
                                    @PathVariable("servicing_id") Long servicingId) {
        Optional<RegistrationDto> registrationDtoOptional = registrationService.create
                (servicingId, doctorId, patientId, registrationDto);
        if (registrationDtoOptional.isEmpty()) {
            return new EntityCreatingResponse<RegistrationDto>().onFailure("Registration");
        }
        return new EntityCreatingResponse<RegistrationDto>().onSuccess(registrationDtoOptional.get());
    }

    @GetMapping("registrations/{doctorID}/{date}")
    @Operation(summary = "get free times for date doctor")
    @PreAuthorize("hasAuthority('employee:write')")
    public List<DoctorAppointment> getFreeTimes(@PathVariable("doctorID") Long doctorId,
                                                @PathVariable("date") Date date) {
        return doctorAppointmentService.getFreeTimes(doctorId, date);
    }

    @PostMapping("registrations/{doctorID}")
    @Operation(summary = "set start and end times for date doctor")
    @PreAuthorize("hasAuthority('employee:write')")
    public List<DoctorAppointment> setFreeTimes(@PathVariable("doctorID") Long doctorId,
                                                @RequestParam("date") Date date,
                                                @RequestParam("job_start_time") Long jobStartTime,
                                                @RequestParam("job_end_time") Long jobEndTime) {
        return doctorAppointmentService.setFreeTimes(doctorId, date, jobStartTime, jobEndTime);
    }

    @GetMapping("/registrations/{id}")
    @Operation(summary = "get by registration id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<RegistrationDto> registrationDtoOptional = registrationService.getById(id);
        if (registrationDtoOptional.isPresent()) {
            return new EntityLookupResponse<RegistrationDto>().onSuccess(registrationDtoOptional.get());
        }
        return new EntityLookupResponse<RegistrationDto>().onFailure("Registration");
    }

    @DeleteMapping("/registrations/{id}")
    @Operation(summary = "delete by registration id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<RegistrationDto> registrationDtoOptional = registrationService.getById(id);
        if (registrationDtoOptional.isPresent()) {
            registrationService.deleteById(id);
            return new EntityDeletingResponse<RegistrationDto>().onSuccess(registrationDtoOptional.
                    get(), "Registration");
        }
        return new EntityLookupResponse<RegistrationDto>().onFailure("Registration");
    }

    @PutMapping("/registrations/{id}/{doctor_id}")
    @Operation(summary = "update by registration id")
    @PreAuthorize("hasAuthority('employee:write')")
    public ResponseEntity<?> update(@RequestBody RegistrationDto registrationDto, @PathVariable("id") Long id
            , @PathVariable("doctor_id") Long doctor_id) {
        Optional<RegistrationDto> registrationDtoOptional = registrationService.update(registrationDto, id, doctor_id);
        if (registrationDtoOptional.isEmpty()) {
            return new EntityUpdatingResponse<RegistrationDto>().onFailure("Registration");
        }
        return new EntityUpdatingResponse<RegistrationDto>().onSuccess(registrationDtoOptional.get());
    }
}


