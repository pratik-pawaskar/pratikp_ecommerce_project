package com.example.ecommerceproject.advice;

import com.example.ecommerceproject.dto.ErrorDTO;
import com.example.ecommerceproject.exceptions.CategoryServiceException;
import com.example.ecommerceproject.exceptions.ProductServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(CategoryServiceException.class)
    public ResponseEntity<ErrorDTO> categoryServiceErrorHandler(Exception e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(e.getMessage());
        ResponseEntity<ErrorDTO> response = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ErrorDTO> productServiceErrorHandler(Exception e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(e.getMessage());
        ResponseEntity<ErrorDTO> response = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
        return response;
    }
}
