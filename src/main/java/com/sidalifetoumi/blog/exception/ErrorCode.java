package com.sidalifetoumi.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"POST_NOT_FOUND"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,"VALIDATION_ERROR");

    private final HttpStatus status;
    private final String code;

    ErrorCode(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

}
