package com.feedback.app.exception;

public class AuthenticationFailureException extends FeedbackBaseException{
    public AuthenticationFailureException(String message) {
        super(message);
    }
}
