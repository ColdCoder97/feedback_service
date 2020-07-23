package com.feedback.app.dao.interfaces;

import com.feedback.app.entity.Feedback;

import java.util.List;

public interface FeedbacksTrackerInterface {
    public boolean insertFeedback(Feedback feedback);
    public Feedback editFeedback(String id, Feedback Feedback);
    public boolean deleteFeedback(String id);
    public List<Feedback> viewFeedbacks(String id);
}
