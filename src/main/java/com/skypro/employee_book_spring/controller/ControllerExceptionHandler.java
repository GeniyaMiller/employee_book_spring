package com.skypro.employee_book_spring.controller;

import com.skypro.employee_book_spring.exception.EmployeeDataException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EmployeeDataException.class)
    public ResponseEntity<String> handleEmployeeDataException(EmployeeDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
