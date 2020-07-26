package com.feedback.app.dao.interfaces;

import com.feedback.app.entity.Feedback;

import java.util.List;

/**
 * The interface program helps to implements an application feedback
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */

public interface FeedbacksTrackerInterface {
    public boolean insertFeedback(Feedback feedback);
    public Feedback editFeedback(String id, Feedback Feedback);
    public boolean deleteFeedback(String id);
    public List<Feedback> viewFeedbacks(String id);
}
