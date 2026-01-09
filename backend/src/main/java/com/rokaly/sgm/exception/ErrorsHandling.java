package com.rokaly.sgm.exception;

import com.rokaly.sgm.dto.CustomErrorsResponse;
import com.rokaly.sgm.dto.ErrorsResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorsHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorsResponse>> error400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErrorsResponse::new).toList());
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<CustomErrorsResponse> businessRuleError(BusinessRuleException ex) {
        return ResponseEntity.unprocessableEntity().body(new CustomErrorsResponse(ex.getMessage()));
    }

}
