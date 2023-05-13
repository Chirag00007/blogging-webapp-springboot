package com.chirag.main.exceptions;

import com.chirag.main.payloads.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleException(ResourceNotFoundException e){
       String message = e.getMessage();
         ApiResponse apiResponse = new ApiResponse(false, message);
         return new ResponseEntity<>(
                    apiResponse,
                    org.springframework.http.HttpStatus.NOT_FOUND
         );
    }
}
