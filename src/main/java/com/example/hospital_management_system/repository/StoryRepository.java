package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.domain.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story,Long> {
}
