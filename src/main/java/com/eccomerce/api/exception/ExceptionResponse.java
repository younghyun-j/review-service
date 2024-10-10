package com.eccomerce.api.exception;

import org.springframework.http.HttpStatus;

public record ExceptionResponse(
        HttpStatus status,
        String message
) {
    public static ExceptionResponse from(ExceptionCode exceptionCode) {
        return new ExceptionResponse(exceptionCode.getHttpStatus(), exceptionCode.getMessage());
    }

    public static ExceptionResponse of(HttpStatus status, String message) {
        return new ExceptionResponse(status, message);
    }
}
