package com.htk.ecommerce.exceptions;

import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException {

    @Getter
    String fieldName;

    public ResourceNotFoundException(String fieldName) {
        super(String.format("Field %s was not found", fieldName));
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
}
