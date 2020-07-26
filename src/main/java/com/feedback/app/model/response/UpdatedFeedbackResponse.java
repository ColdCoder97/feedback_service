package com.feedback.app.model.response;

import com.feedback.app.entity.Feedback;
import lombok.Data;

import java.util.List;
/**
 * The model response program helps to provide response entity as updated feedback
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
@Data
public class UpdatedFeedbackResponse extends FeedbackAppBaseResponse{
    private Feedback feedbacks;
}
