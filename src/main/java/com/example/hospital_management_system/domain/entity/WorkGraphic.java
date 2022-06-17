package com.example.hospital_management_system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table(name = "work_graphic")
public class WorkGraphic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "week_day")
    private DayOfWeek weekDay;
    @Column(name = "start")
    private Integer start;
    @Column(name = "end")
    private Integer end;
    @ManyToOne
    @JoinColumn(name="doctor_id")
    @JsonIgnore
    private Doctor doctor;

    public WorkGraphic(){

    }
    public WorkGraphic(DayOfWeek weekDay, Integer start, Integer end) {
        this.weekDay = weekDay;
        this.start = start;
        this.end = end;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}