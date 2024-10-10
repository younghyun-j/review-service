package com.eccomerce.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    // Product
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // Review
    DUPLICATE_REVIEW("상품에 대한 리뷰가 이미 존재합니다.", HttpStatus.CONFLICT),

    // Image
    BLANK_FILE_NAME("파일 이름은 비어있을 수 없습니다", HttpStatus.BAD_REQUEST),
    INVALID_FILE_EXTENSION("잘못된 파일 형식입니다", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_FILE_EXTENSION("지원하지않는 파일 확장자입니다", HttpStatus.BAD_REQUEST),
    FILE_NOT_PROVIDED("전달된 파일이 없습니다", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
