package com.example.hospital_management_system.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EntityLookupResponse <T>{
    public ResponseEntity<?>onFailure(String entityName){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entityName + " with given params does not exist.");
    }
    public ResponseEntity<?>onSuccess(T entityDto){
        return ResponseEntity.ok().body(entityDto);
    }
}
