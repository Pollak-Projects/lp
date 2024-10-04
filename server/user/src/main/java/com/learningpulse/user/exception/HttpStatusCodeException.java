package com.learningpulse.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class HttpStatusCodeException extends RuntimeException {
    @Getter
    private final HttpStatus statusCode;

    public HttpStatusCodeException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
