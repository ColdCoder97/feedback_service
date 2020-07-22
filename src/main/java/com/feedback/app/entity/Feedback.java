package com.feedback.app.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Feedback {
    @Id
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
    private long createdAt; //millisecond storing
    private long updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIsContentRelevant() {
        return isContentRelevant;
    }

    public void setIsContentRelevant(String isContentRelevant) {
        this.isContentRelevant = isContentRelevant;
    }

    public String getContentHandsOn() {
        return contentHandsOn;
    }

    public void setContentHandsOn(String contentHandsOn) {
        this.contentHandsOn = contentHandsOn;
    }

    public String getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(String proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public int getRatingContent() {
        return ratingContent;
    }

    public void setRatingContent(int ratingContent) {
        this.ratingContent = ratingContent;
    }

    public int getRatingHandsOn() {
        return ratingHandsOn;
    }

    public void setRatingHandsOn(int ratingHandsOn) {
        this.ratingHandsOn = ratingHandsOn;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
