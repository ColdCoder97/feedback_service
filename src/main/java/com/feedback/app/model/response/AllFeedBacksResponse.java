package com.feedback.app.model.response;

import com.feedback.app.entity.Feedback;
import lombok.Data;
import java.util.List;

@Data
public class AllFeedBacksResponse extends FeedbackAppBaseResponse {
     private List<Feedback> feedbacks;
}
