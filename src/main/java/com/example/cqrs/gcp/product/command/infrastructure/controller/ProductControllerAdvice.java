package com.example.cqrs.gcp.product.command.infrastructure.controller;


import com.example.cqrs.gcp.product.command.domain.exception.InvalidProductDataException;
import com.example.cqrs.gcp.product.command.domain.exception.ProductNotFoundException;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ProductControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiError handleInvalidArguments(MethodArgumentNotValidException e) {

        var errors = e.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe -> Optional.ofNullable(fe.getDefaultMessage()).orElse("")));

        return new ApiError("P-001", "Invalid arguments", errors);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleIllegalStateException(IllegalArgumentException e) {

        return new ApiError("P-002", e.getMessage(), Collections.emptyMap());
    }

    @ExceptionHandler(InvalidProductDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleInvalidProductDataException(InvalidProductDataException e) {
        return new ApiError("P-003", e.getMessage(), Collections.emptyMap());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleProductNotFoundException(ProductNotFoundException e) {
        return new ApiError("P-004", e.getMessage(), Collections.emptyMap());
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e,
                                                                  HttpServletRequest request) {

        log.error("Error while processing request {}", request.getRequestURI(), e);
        return new ApiError("P-005", "Error while processing request", Collections.emptyMap());
    }


}
