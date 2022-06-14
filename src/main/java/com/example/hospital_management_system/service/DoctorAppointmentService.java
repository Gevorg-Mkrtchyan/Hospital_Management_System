package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.entity.DoctorAppointment;
import com.example.hospital_management_system.domain.entity.Registration;
import com.example.hospital_management_system.domain.enums.AppointmentStatus;
import com.example.hospital_management_system.repository.DoctorAppointmentRepository;
import com.example.hospital_management_system.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DoctorAppointmentService {
    private final RegistrationRepository registrationRepository;
    private final DoctorAppointmentRepository doctorAppointmentRepository;

    public DoctorAppointmentService(RegistrationRepository registrationRepository,
                                    DoctorAppointmentRepository doctorAppointmentRepository) {
        this.registrationRepository = registrationRepository;
        this.doctorAppointmentRepository = doctorAppointmentRepository;
    }

    public List<DoctorAppointment> getFreeTimes(Long doctorId, Date date) {
        Registration registration = registrationRepository.findRegistrationByDoctorIdAndRegDay(doctorId, date);
        if (registration == null) {
            int time = 9;
            while (time < 18) {
                if (time == 13) {
                    time++;
                }
                DoctorAppointment doctorAppointment = new DoctorAppointment();
                doctorAppointment.setDoctorId(doctorId);
                doctorAppointment.setDate(date);
                doctorAppointment.setStartTime(time + ":00");
                doctorAppointment.setEndTime(time + ":30");
                doctorAppointmentRepository.save(doctorAppointment);
                time++;
            }
        }
        return doctorAppointmentRepository.findDoctorAppointmentByDoctorIdAndDateAndAppointmentStatus
                (doctorId, date, AppointmentStatus.FREE);

    }
}







