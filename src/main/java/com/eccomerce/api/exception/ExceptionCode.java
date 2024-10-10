package com.eccomerce.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_REVIEW("상품에 대한 리뷰가 이미 존재합니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
