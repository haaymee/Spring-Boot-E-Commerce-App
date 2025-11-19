package com.htk.ecommerce.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;

    public APIException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public APIException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
