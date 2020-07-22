package com.feedback.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntryAlreadyExistException extends RuntimeException{
    public EntryAlreadyExistException(String message) {
        super(message);
    }
}
