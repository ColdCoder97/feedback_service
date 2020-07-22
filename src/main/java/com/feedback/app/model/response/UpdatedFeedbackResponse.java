package com.feedback.app.model.response;

import com.feedback.app.entity.Feedback;
import lombok.Data;

import java.util.List;
@Data
public class UpdatedFeedbackResponse extends FeedbackAppBaseResponse{
    private Feedback feedbacks;
}
