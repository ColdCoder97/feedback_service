package com.feedback.app.utils;

import com.feedback.app.model.response.FeedBackFailureResponse;

public class FailureUtils {

    public static FeedBackFailureResponse getFailureResponse(String responseId, String message, String error, int status) {

        FeedBackFailureResponse failureresponse=new FeedBackFailureResponse();
        failureresponse.setResponseId(responseId);
        failureresponse.setMessage(message);
        failureresponse.setError(error);
        failureresponse.setStatus(status);
        return failureresponse;
    }
}
