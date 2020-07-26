package com.feedback.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The exception program helps to implements an feedback base format
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FeedbackBaseException extends RuntimeException{
    public FeedbackBaseException(String message) {
        super(message);
    }
}
