package com.feedback.app.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * The entity program helps to implements an application feedback
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
@Document
@Data
public class Feedback {
    @Id
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
    private long createdAt; //millisecond storing
    private long updatedAt;

}
