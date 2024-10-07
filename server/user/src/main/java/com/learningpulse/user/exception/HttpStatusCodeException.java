package com.learningpulse.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpStatusCodeException extends RuntimeException {
    private final HttpStatus statusCode;

    public HttpStatusCodeException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
