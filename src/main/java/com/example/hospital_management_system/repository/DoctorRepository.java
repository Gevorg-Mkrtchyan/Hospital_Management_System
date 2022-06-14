package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.domain.entity.Doctor;
import com.example.hospital_management_system.domain.enums.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor>findAllByDepartment(Department department);
}
