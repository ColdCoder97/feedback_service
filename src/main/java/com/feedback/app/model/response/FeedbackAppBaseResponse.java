package com.feedback.app.model.response;

import lombok.Data;
/**
 * The model response program helps to provide response entity as feedback
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
@Data
public class FeedbackAppBaseResponse {
    private String responseId;
    private String message;
    private int status;
}
