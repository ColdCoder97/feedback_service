package com.feedback.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends FeedbackBaseException {

    public BadRequestException(String message) {
        super(message);
    }
}
