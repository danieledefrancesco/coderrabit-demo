package com.tuimm.learningpath.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TripAlreadyStartedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {TripAlreadyStartedException.class})
    protected ResponseEntity<Object> handleTripAlreadyStarted(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex,
                ErrorResponseBuilder.buildErrorBody(ex, 422),
                ErrorResponseBuilder.buildHttpHeaders(),
                HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
