package com.example.hospital_management_system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long id;
    @Column(name = "reg_dey",nullable = false)
    private Date regDay;
    @Column(name = "time",nullable = false)
    private String time;
    @ManyToOne
    @JoinColumn(name = "patient_id", foreignKey = @ForeignKey(name = "registration_patient_fk"))
    @JsonIgnore
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id", foreignKey = @ForeignKey(name = "registration_doctor_fk"))
    @JsonIgnore
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "servicing_id", foreignKey = @ForeignKey(name = "registration_servicing_fk"))
    @JsonIgnore
    private Servicing servicing;

    public Registration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegDay() {
        return regDay;
    }

    public void setRegDay(Date regDate) {
        this.regDay = regDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Servicing getServicing() {
        return servicing;
    }

    public void setServicing(Servicing servicing) {
        this.servicing = servicing;
    }
}
