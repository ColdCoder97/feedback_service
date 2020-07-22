package com.feedback.app.model.response;

import lombok.Data;

@Data
public class FeedBackFailureResponse extends FeedbackAppBaseResponse {

    private String error;
}
