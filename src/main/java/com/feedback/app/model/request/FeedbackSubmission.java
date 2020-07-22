package com.feedback.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
    private int ratingContent;
    private int ratingHandsOn;
    private String comment;
}
