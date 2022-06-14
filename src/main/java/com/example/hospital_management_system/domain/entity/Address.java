package com.example.hospital_management_system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address",uniqueConstraints =
        @UniqueConstraint(columnNames = {"country", "city","street","building","apartment"}))
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    @JsonIgnore
    private Long id;
    @Column(name = "country", length = 25)
    private String country;
    @Column(name = "city", length = 25)
    private String city;
    @Column(name = "street", length = 25)
    private String street;
    @Min(1)
    @Column(name = "building", length = 25)
    private String building;
    @Min(1)
    @Column(name = "apartment", length = 25)
    private String apartment;
    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private Set<Patient> patients = new HashSet<>();

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
