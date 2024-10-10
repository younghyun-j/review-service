package com.eccomerce.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 비즈니스 로직 예외
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {
        ExceptionResponse response = ExceptionResponse.from(e.getExceptionCode());
        return ResponseEntity.status(response.status()).body(response);
    }

    /**
     * 요청 본문 유효성 예외
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldExceptionResponse>> handleFieldException(MethodArgumentNotValidException e) {
        List<FieldExceptionResponse> response = FieldExceptionResponse.of(e.getBindingResult());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }
}
