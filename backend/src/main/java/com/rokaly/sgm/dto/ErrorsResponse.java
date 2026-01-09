package com.rokaly.sgm.dto;

import org.springframework.validation.FieldError;

public record ErrorsResponse(String field, String message) {

    public ErrorsResponse(FieldError e) {
        this(e.getField(), e.getDefaultMessage());
    }
}
