package com.eccomerce.api.exception.dto;

import org.springframework.validation.BindingResult;

import java.util.List;

public record FieldExceptionResponse(
        String filed,
        Object rejectedValue,
        String message
) {
    public static List<FieldExceptionResponse> of(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldExceptionResponse(
                        fieldError.getField(),
                        fieldError.getRejectedValue(),
                        fieldError.getDefaultMessage()))
                .toList();
    }
}
