package com.example.hospital_management_system.domain.dto;


import com.example.hospital_management_system.domain.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DoctorAppointmentDto {
    private Date date;
    private String startTime;
    private String endTime;
    private AppointmentStatus appointmentStatus;
    private Long doctorId;
}
