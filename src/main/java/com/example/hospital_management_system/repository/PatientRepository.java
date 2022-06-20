package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Optional<Patient> findBySsn(String ssn);
    Optional<Patient> findByPhone(String phone);
    Optional<Patient> findByActivationCode(String activationCode);

}
