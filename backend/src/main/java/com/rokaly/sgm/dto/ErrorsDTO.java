package com.rokaly.sgm.dto;

import org.springframework.validation.FieldError;

public record ErrorsDTO(String field, String message) {

    public ErrorsDTO(FieldError e) {
        this(e.getField(), e.getDefaultMessage());
    }
}
