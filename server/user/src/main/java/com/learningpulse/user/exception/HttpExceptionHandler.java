package com.learningpulse.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HttpStatusCodeException.class})
    protected ResponseEntity<String> handleHttpStatusCodeException(RuntimeException ex) {
        HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) ex;
        return ResponseEntity
                .status(httpStatusCodeException.getStatusCode().value())
                .body(httpStatusCodeException.getMessage());
    }
}
