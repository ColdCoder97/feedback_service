package com.feedback.app.utils;

import com.feedback.app.model.response.FeedBackFailureResponse;

/**
 * The response format program helps to understand user
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
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
