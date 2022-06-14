package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.domain.entity.DoctorAppointment;
import com.example.hospital_management_system.domain.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment,Long> {
    List<DoctorAppointment> findDoctorAppointmentByDoctorIdAndDateAndAppointmentStatus(Long doctorId, Date date,
                                                                            AppointmentStatus appointmentStatus);
    DoctorAppointment findByDoctorIdAndDateAndStartTime(Long doctorId,Date date,String startTime);
}
