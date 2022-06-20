package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.entity.Doctor;
import com.example.hospital_management_system.domain.entity.DoctorAppointment;
import com.example.hospital_management_system.domain.entity.Registration;
import com.example.hospital_management_system.domain.entity.WorkGraphic;
import com.example.hospital_management_system.domain.enums.AppointmentStatus;
import com.example.hospital_management_system.repository.DoctorAppointmentRepository;
import com.example.hospital_management_system.repository.DoctorRepository;
import com.example.hospital_management_system.repository.RegistrationRepository;
import com.example.hospital_management_system.repository.WorkGraphicRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DoctorAppointmentService {
    private final RegistrationRepository registrationRepository;
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorRepository doctorRepository;
    private final WorkGraphicRepository workGraphicRepository;
    public DoctorAppointmentService(RegistrationRepository registrationRepository,
                                    DoctorAppointmentRepository doctorAppointmentRepository,
                                    DoctorRepository doctorRepository, WorkGraphicRepository workGraphicRepository) {
        this.registrationRepository = registrationRepository;
        this.doctorAppointmentRepository = doctorAppointmentRepository;
        this.doctorRepository = doctorRepository;
        this.workGraphicRepository = workGraphicRepository;
    }
    public List<DoctorAppointment> getFreeTimes(Long doctorId, Date date) {
        List<Registration> registrations = registrationRepository.findRegistrationByDoctorIdAndRegDay(doctorId, date);
        if (registrations.size() == 0) {
            if (doctorAppointmentRepository.findByDate(date).size() == 0) {
                WorkGraphic workGraphic = workGraphicRepository.findByDoctorIdAndWeekDay(doctorId, date.toLocalDate().getDayOfWeek());
                Doctor doctor = doctorRepository.findById(doctorId).get();
                int time = workGraphic.getStart();
                int count = 1;
                while (time < workGraphic.getEnd()) {
                    DoctorAppointment doctorAppointment = new DoctorAppointment();
                    doctorAppointment.setDoctor(doctor);
                    doctorAppointment.setDate(date);
                    if (count % 2 != 0) {
                        doctorAppointment.setStartTime(time + ":00");
                        doctorAppointment.setEndTime(time + ":30");
                    }
                    if (count % 2 == 0) {
                        doctorAppointment.setStartTime(time + ":30");
                        doctorAppointment.setEndTime(time + 1 + ":00");
                        time++;
                    }
                    count++;
                    doctorAppointmentRepository.save(doctorAppointment);
                }
            }
        }
        return doctorAppointmentRepository.findDoctorAppointmentByDoctorIdAndDateAndAppointmentStatus
                (doctorId, date, AppointmentStatus.FREE);
    }
}







