package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.dto.DoctorDto;
import com.example.hospital_management_system.domain.entity.*;
import com.example.hospital_management_system.domain.enums.AppointmentStatus;
import com.example.hospital_management_system.domain.enums.Department;
import com.example.hospital_management_system.domain.enums.Role;
import com.example.hospital_management_system.domain.enums.Status;
import com.example.hospital_management_system.domain.mapper.DoctorMapper;
import com.example.hospital_management_system.repository.DoctorAppointmentRepository;
import com.example.hospital_management_system.repository.DoctorRepository;
import com.example.hospital_management_system.repository.RegistrationRepository;
import com.example.hospital_management_system.repository.WorkGraphicRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final WorkGraphicRepository workGraphicRepository;
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final RegistrationRepository registrationRepository;

    public DoctorService(DoctorMapper doctorMapper, DoctorRepository doctorRepository, PasswordEncoder passwordEncoder, WorkGraphicRepository workGraphicRepository, DoctorAppointmentRepository doctorAppointmentRepository, RegistrationRepository registrationRepository) {
        this.doctorMapper = doctorMapper;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
        this.workGraphicRepository = workGraphicRepository;
        this.doctorAppointmentRepository = doctorAppointmentRepository;
        this.registrationRepository = registrationRepository;
    }

    public Optional<DoctorDto> create(DoctorDto doctorDto) {
        Doctor doctorToSave = doctorMapper.convertToEntity(doctorDto);
        doctorToSave.getUser().setPassword(passwordEncoder.encode(doctorToSave.getUser().getPassword()));
        doctorToSave.getUser().setRole(Role.EMPLOYEE);
        doctorToSave.getUser().setStatus(Status.ACTIVE);
        Doctor savedDoctor = doctorRepository.save(doctorToSave);
        return Optional.of(doctorMapper.convertToDto(savedDoctor));
    }

    public void addDoctorWorkGraphic(WorkGraphic workGraphic, Long doctorId) {
        workGraphic.setDoctor(doctorRepository.findById(doctorId).get());
        workGraphicRepository.save(workGraphic);
    }

    public void updateWorkGraphic(Long doctorId, DayOfWeek weekDay, Integer start, Integer end) {
        List<DoctorAppointment> doctorAppointments = doctorAppointmentRepository.findByDateAfterAndDoctorId(Date.valueOf(LocalDate.now()), doctorId);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/resources/callForTimeUpdate.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Patient patient;
        if (doctorAppointments.size() != 0) {
            for (DoctorAppointment doctorApp : doctorAppointments) {
                if (doctorApp.getDate().toLocalDate().getDayOfWeek().equals(weekDay)) {
                    if (doctorApp.getAppointmentStatus() == AppointmentStatus.BUSY) {
                        Registration registration = registrationRepository.
                                findRegistrationByDoctorIdAndRegDayAndTime(doctorId,
                                        doctorApp.getDate(), doctorApp.getStartTime());
                        patient = registration.getPatient();
                        registrationRepository.delete(registration);
                        try {
                            fileWriter.write(patient.getName() + " " + patient.getSurname() + " "
                                    + patient.getPhone() + " " + registration.getTime() + " "
                                    + registration.getRegDay());
                            fileWriter.write("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        doctorAppointmentRepository.delete(doctorApp);
                    }
                }
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        WorkGraphic workGraphic = workGraphicRepository.findByDoctorIdAndWeekDay(doctorId, weekDay);
        workGraphic.setStart(start);
        workGraphic.setEnd(end);
        workGraphicRepository.save(workGraphic);
    }

    public Optional<DoctorDto> getById(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(doctorMapper.convertToDto(doctorOptional.get()));
    }

    public List<DoctorDto> getDoctors() {
        return doctorMapper.mapAllToDoctorDto(doctorRepository.findAll());
    }

    public List<Doctor> getAllByDepartment(Department department) {
        return doctorRepository.findAllByDepartment(department);
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    public Optional<DoctorDto> update(DoctorDto doctorDto, Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            return Optional.empty();
        }
        Doctor doctorToSave = doctorMapper.convertToEntity(doctorDto);
        doctorOptional.get().setName(doctorToSave.getName());
        doctorOptional.get().setSurname(doctorToSave.getSurname());
        doctorOptional.get().setDepartment(doctorToSave.getDepartment());
        doctorOptional.get().setProfession(doctorToSave.getProfession());
        doctorOptional.get().setPhone(doctorToSave.getPhone());
        doctorOptional.get().getUser().setEmail(doctorToSave.getUser().getEmail());
        doctorOptional.get().getUser().setPassword(passwordEncoder.encode(doctorToSave.getUser().getPassword()));
        Doctor savedDoctor = doctorRepository.save(doctorOptional.get());
        return Optional.of(doctorMapper.convertToDto(savedDoctor));
    }
}

