package com.example.hospital_management_system.domain.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

public interface BaseMapper<T,E> {
    T convertToEntity(E dto);
    E convertToDto(T entity);
    default Collection<T> convertToEntityColl(Collection<E> dtoCollection) {
        return dtoCollection.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
    default Collection<E> convertToDtoColl(Collection<T> entityCollection) {
        return entityCollection.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}