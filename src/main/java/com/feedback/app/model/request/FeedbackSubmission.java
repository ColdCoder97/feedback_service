package com.feedback.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
/**
 * The model request program helps to redirect api to Feedback repository
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FeedbackSubmission {
    private String id;
    private String name;
    private long employeeId;
    private String courseName;
    private String isContentRelevant;
    private String contentHandsOn;
    private String proficiencyLevel;
    private String ratingContent;
    private String ratingHandsOn;
    private String comment;
}
