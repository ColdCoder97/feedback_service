package com.feedback.app.dao;

import com.feedback.app.controller.RegistrationController;
import com.feedback.app.dao.interfaces.FeedbacksTrackerInterface;
import com.feedback.app.entity.Feedback;
import com.feedback.app.entity.UserRegister;
import com.feedback.app.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Created by 1430208-Yamini S
 * Dao Class for user details adding based on request of logged in user.
 */
@Repository
@Slf4j
public class FeedbackDaoImpl implements FeedbacksTrackerInterface {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private MongoTemplate mongoTemplate;


	@Override
	public boolean insertFeedback(Feedback feedback) {
		Query query = new Query();
		query.addCriteria(Criteria.where("employeeId").is(feedback.getEmployeeId()).andOperator(Criteria.where("name").is(feedback.getName())));
		UserRegister isEmployeePresent = mongoTemplate.findOne(query, UserRegister.class);
		if (isEmployeePresent != null) {
			logger.info("Entry submitted under Employee:"+feedback.getEmployeeId());
			mongoTemplate.save(feedback);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Feedback editFeedback(String id, Feedback feedback) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		com.feedback.app.entity.Feedback existFeedback = mongoTemplate.findOne(query, Feedback.class);
		if (existFeedback != null) {
			existFeedback.setCourseName(feedback.getCourseName());
			existFeedback.setIsContentRelevant(feedback.getIsContentRelevant());
			existFeedback.setContentHandsOn(feedback.getContentHandsOn());
			existFeedback.setProficiencyLevel(feedback.getProficiencyLevel());
			existFeedback.setRatingContent(feedback.getRatingContent());
			existFeedback.setRatingHandsOn(feedback.getRatingHandsOn());
			existFeedback.setComment(feedback.getComment());
			mongoTemplate.save(existFeedback);
		}

		return existFeedback;
	}

	@Override
	public boolean deleteFeedback(String id) {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(id));
		    Feedback isRecordPresent = mongoTemplate.findOne(query, Feedback.class);
		    if (isRecordPresent != null) {
			   logger.info("Feedback deleted success");
				mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Feedback.class);
				return true;
			}
		return false;
	}

	@Override
	public List<Feedback> viewFeedbacks(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		List<Feedback> isEmployeePresent = mongoTemplate.find(query,Feedback.class);
		if (isEmployeePresent != null) {
			logger.info("Feedback Existed List for id");
			return isEmployeePresent;
		} else {
			return null;
		}
    }

	public List<Feedback> viewAllFeedbacks() {
		List<Feedback> allFeedbackList = mongoTemplate.findAll(Feedback.class);
		if (allFeedbackList != null) {
			logger.info("Feedback Existed List for All");
			return allFeedbackList;
		} else {
			return null;
		}
	}
    }
