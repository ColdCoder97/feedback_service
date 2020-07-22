package com.feedback.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataStoreException extends RuntimeException{
    public DataStoreException(String message) {
        super(message);
    }
}
