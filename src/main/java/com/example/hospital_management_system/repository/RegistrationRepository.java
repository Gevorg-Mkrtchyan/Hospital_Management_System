package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.domain.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    List<Registration> findRegistrationByDoctorIdAndRegDay(Long doctorID, Date regDay);

}
