package com.example.hospital_management_system.domain.mapper;

import com.example.hospital_management_system.domain.dto.ServicingDto;
import com.example.hospital_management_system.domain.entity.Servicing;
import org.springframework.stereotype.Component;

@Component
public class ServicingMapper implements BaseMapper<Servicing, ServicingDto>{
    @Override
    public Servicing convertToEntity(ServicingDto dto) {
        Servicing servicing = new Servicing();
        servicing.setName(dto.getName());
        servicing.setPrice(dto.getPrice());
        return servicing;
    }

    @Override
    public ServicingDto convertToDto(Servicing entity) {
        ServicingDto servicingDto = new ServicingDto();
        servicingDto.setName(entity.getName());
        servicingDto.setPrice(entity.getPrice());
        return servicingDto;
    }
}
