package com.feedback.app.model.response;

import lombok.Data;

@Data
public class FeedbackAppBaseResponse {
    private String responseId;
    private String message;
    private int status;
}
