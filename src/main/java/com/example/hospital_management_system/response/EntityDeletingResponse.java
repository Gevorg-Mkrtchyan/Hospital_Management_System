package com.example.hospital_management_system.response;

import org.springframework.http.ResponseEntity;

public class EntityDeletingResponse <T>{
    public ResponseEntity<?> onSuccess(T entityDto,String entityName){
        return ResponseEntity.ok().body("The following "+entityName+" was successfully deleted \n"+entityDto);
    }
}
