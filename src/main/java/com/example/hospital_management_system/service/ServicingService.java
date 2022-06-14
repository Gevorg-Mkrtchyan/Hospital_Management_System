package com.example.hospital_management_system.service;

import com.example.hospital_management_system.domain.dto.ServicingDto;
import com.example.hospital_management_system.domain.entity.Servicing;
import com.example.hospital_management_system.domain.mapper.ServicingMapper;
import com.example.hospital_management_system.repository.ServicingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicingService {
    private final ServicingRepository servicingRepository;
    private final ServicingMapper servicingMapper;

    public ServicingService(ServicingRepository servicingRepository, ServicingMapper servicingMapper) {
        this.servicingRepository = servicingRepository;
        this.servicingMapper = servicingMapper;
    }

    public Optional<ServicingDto> create(ServicingDto servicingDto) {
        Servicing servicingToSave = servicingMapper.convertToEntity(servicingDto);
        Servicing savedServicing = servicingRepository.save(servicingToSave);
        return Optional.of(servicingMapper.convertToDto(savedServicing));
    }

    public Optional<ServicingDto> getById(Long id) {
        Optional<Servicing> optionalServicing = servicingRepository.findById(id);
        if (optionalServicing.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(servicingMapper.convertToDto(optionalServicing.get()));
    }

    public List<Servicing> getAll() {
        return servicingRepository.findAll();
    }

    public Optional<ServicingDto> update(ServicingDto servicingDto, Long id) {
        Optional<Servicing> servicingOptional = servicingRepository.findById(id);
        if (servicingOptional.isEmpty()) {
            return Optional.empty();
        }
        Servicing servicingToSave = servicingMapper.convertToEntity(servicingDto);
        servicingOptional.get().setName(servicingToSave.getName());
        servicingOptional.get().setPrice(servicingToSave.getPrice());
        Servicing savedServicing = servicingRepository.save(servicingOptional.get());
        return Optional.of(servicingMapper.convertToDto(savedServicing));
    }

    public void deleteByID(Long id) {
        servicingRepository.deleteById(id);
    }
}
