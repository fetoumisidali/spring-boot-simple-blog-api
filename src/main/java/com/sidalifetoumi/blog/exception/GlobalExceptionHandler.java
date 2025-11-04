package com.sidalifetoumi.blog.exception;


import com.sidalifetoumi.blog.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        // Collect all validation errors as a list
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.putIfAbsent(error.getField(), error.getDefaultMessage())
                );

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.VALIDATION_ERROR.getStatus().value(),
                ErrorCode.VALIDATION_ERROR.getCode(),
                errors,
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, ErrorCode.VALIDATION_ERROR.getStatus());
    }


    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.POST_NOT_FOUND.getStatus().value(),
                ErrorCode.POST_NOT_FOUND.getCode(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, ErrorCode.POST_NOT_FOUND.getStatus());
    }


}
