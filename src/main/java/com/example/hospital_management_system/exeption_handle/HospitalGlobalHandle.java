package com.example.hospital_management_system.exeption_handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HospitalGlobalHandle {
    @ExceptionHandler
    public ResponseEntity<HospitalIncorrectData> handelException(
            Exception exception) {
        HospitalIncorrectData data = new HospitalIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
