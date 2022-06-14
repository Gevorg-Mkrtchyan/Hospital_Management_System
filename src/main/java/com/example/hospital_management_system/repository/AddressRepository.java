package com.example.hospital_management_system.repository;

import com.example.hospital_management_system.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsAddressByCountryAndCityAndStreetAndBuildingAndApartment(
            String country, String city, String street, String building, String apartment);

    Address getAddressByCountryAndCityAndStreetAndBuildingAndApartment(
            String country, String city,String street, String building, String apartment);
}
