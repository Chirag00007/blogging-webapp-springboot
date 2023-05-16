package com.chirag.main.exceptions;

import com.chirag.main.helpers.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
         @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ApiResponse apiResponse = new ApiResponse(false, errors.toString());
        return new ResponseEntity<>(
                apiResponse,
                org.springframework.http.HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> handleException(HttpRequestMethodNotSupportedException e){
        String message = "Please provide valid arguments";
        ApiResponse apiResponse = new ApiResponse(false, message);
        return new ResponseEntity<>(
                apiResponse,
                org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED
        );
    }
}
