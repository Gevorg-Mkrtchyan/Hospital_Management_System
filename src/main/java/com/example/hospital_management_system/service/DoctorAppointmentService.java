package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.entity.DoctorAppointment;
import com.example.hospital_management_system.domain.entity.Registration;
import com.example.hospital_management_system.domain.enums.AppointmentStatus;
import com.example.hospital_management_system.repository.DoctorAppointmentRepository;
import com.example.hospital_management_system.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            String[] split;
            String readLine;
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader
                        ("src/main/resources/workGraphic.txt"));
                while (true) {
                    readLine = bufferedReader.readLine();
                    if (readLine == null)
                        break;
                    split = readLine.split("-");
                    DoctorAppointment doctorAppointment = new DoctorAppointment();
                    doctorAppointment.setDate(date);
                    doctorAppointment.setStartTime(split[0]);
                    doctorAppointment.setEndTime(split[1]);
                    doctorAppointmentRepository.save(doctorAppointment);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return doctorAppointmentRepository.findDoctorAppointmentByDoctorIdAndDateAndAppointmentStatus(
                doctorId, date, AppointmentStatus.FREE);
    }
}








