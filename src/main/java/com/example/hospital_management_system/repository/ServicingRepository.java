package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.domain.entity.Servicing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicingRepository extends JpaRepository<Servicing,Long> {
}
