package com.tuimm.learningpath.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                ErrorResponseBuilder.buildErrorBody(ex, 404),
                ErrorResponseBuilder.buildHttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }
}
