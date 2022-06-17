package com.example.hospital_management_system.domain.entity;

import com.example.hospital_management_system.domain.enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "doctor_appointment")
public class DoctorAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_appointment")
    private Long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "appointment_status")
    @JsonIgnore
    private AppointmentStatus appointmentStatus = AppointmentStatus.FREE;
    @ManyToOne
    @JoinColumn(name = "doctorId")
    @JsonIgnore
    private Doctor doctor;

    public DoctorAppointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
