package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.dto.RegistrationDto;
import com.example.hospital_management_system.domain.entity.*;
import com.example.hospital_management_system.domain.enums.AppointmentStatus;
import com.example.hospital_management_system.domain.mapper.RegistrationMapper;
import com.example.hospital_management_system.repository.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    private final RegistrationMapper registrationMapper;
    private final RegistrationRepository registrationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ServicingRepository servicingRepository;
    private final DoctorAppointmentRepository doctorAppointmentRepo;

    public RegistrationService(RegistrationMapper registrationMapper, RegistrationRepository registrationRepository,
                               PatientRepository patientRepository, DoctorRepository doctorRepository,
                               ServicingRepository servicingRepository, DoctorAppointmentRepository doctorAppointmentRepo) {
        this.registrationMapper = registrationMapper;
        this.registrationRepository = registrationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.servicingRepository = servicingRepository;
        this.doctorAppointmentRepo = doctorAppointmentRepo;
    }

    public Optional<RegistrationDto> create(Long doctorId, String patientSsn, Long servicingId,
                                            Long doctorAppointmentId) {
        DoctorAppointment doctorAppointment = doctorAppointmentRepo.findById(doctorAppointmentId).get();
        Registration registrationToSave = new Registration();
        registrationToSave.setRegDay(doctorAppointment.getDate());
        registrationToSave.setTime(doctorAppointment.getStartTime());
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        Optional<Patient> patientOptional = patientRepository.findBySsn(patientSsn);
        Optional<Servicing> servicingOptional = servicingRepository.findById(servicingId);
        if (doctorOptional.isEmpty() || patientOptional.isEmpty() || servicingOptional.isEmpty()) {
            return Optional.empty();
        }
        registrationToSave.setDoctor(doctorOptional.get());
        registrationToSave.setPatient(patientOptional.get());
        registrationToSave.setServicing(servicingOptional.get());
        doctorAppointment.setAppointmentStatus(AppointmentStatus.BUSY);
        doctorAppointmentRepo.save(doctorAppointment);
        return Optional.of(registrationMapper.convertToDto(registrationRepository.save(registrationToSave)));
    }

    public Optional<RegistrationDto> getById(Long id) {
        Optional<Registration> registrationOptional = registrationRepository.findById(id);
        if (registrationOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(registrationMapper.convertToDto(registrationOptional.get()));
    }

    public void deleteById(Long id) {
        registrationRepository.deleteById(id);
    }

    public Optional<RegistrationDto> update(RegistrationDto registrationDto, Long id, Long doctorId) {
        Optional<Registration> registrationOptional = registrationRepository.findById(id);
        if (registrationOptional.isEmpty()) {
            return Optional.empty();
        }
        String chengeTime = registrationOptional.get().getTime();
        DoctorAppointment doctorAppointmentChenge = doctorAppointmentRepo.
                findByDoctorIdAndDateAndStartTime(doctorId, registrationDto.getRegDay(), chengeTime);
        doctorAppointmentChenge.setAppointmentStatus(AppointmentStatus.FREE);
        DoctorAppointment doctorAppointment = doctorAppointmentRepo.findByDoctorIdAndDateAndStartTime(doctorId,
                registrationDto.getRegDay(), registrationDto.getTime());
        doctorAppointment.setAppointmentStatus(AppointmentStatus.BUSY);
        doctorAppointmentRepo.save(doctorAppointment);
        Registration registrationToSave = registrationMapper.convertToEntity(registrationDto);
        registrationOptional.get().setRegDay(registrationToSave.getRegDay());
        registrationOptional.get().setTime(registrationToSave.getTime());
        Registration savedRegistration = registrationRepository.save(registrationOptional.get());
        return Optional.of(registrationMapper.convertToDto(savedRegistration));
    }
}

