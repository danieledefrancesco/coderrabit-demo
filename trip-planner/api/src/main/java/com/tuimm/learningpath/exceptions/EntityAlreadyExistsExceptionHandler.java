package com.tuimm.learningpath.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityAlreadyExistsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    protected ResponseEntity<Object> handleEntityAlreadyExists(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                ErrorResponseBuilder.buildErrorBody(ex, 409),
                ErrorResponseBuilder.buildHttpHeaders(),
                HttpStatus.CONFLICT, request);
    }
}
