package com.example.hospital_management_system.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EntityCreatingResponse<T>{
    public ResponseEntity<?>onFailure(String entityName){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("There is a/an " + entityName + " with this parameter" );
    }
    public ResponseEntity<?> onSuccess(T entityDto){
        return ResponseEntity.ok().body(entityDto);
    }
}
