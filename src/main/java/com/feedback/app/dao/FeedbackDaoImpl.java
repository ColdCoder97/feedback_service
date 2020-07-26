package com.feedback.app.dao;

import com.feedback.app.controller.RegistrationController;
import com.feedback.app.dao.interfaces.FeedbacksTrackerInterface;
import com.feedback.app.entity.Feedback;
import com.feedback.app.entity.UserRegister;
import com.feedback.app.exception.BadRequestException;
import com.feedback.app.exception.EntryAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository program helps to implements an application feedback
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */

@Repository
@Slf4j
public class FeedbackDaoImpl implements FeedbacksTrackerInterface {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * @method insertFeedback with Feedback entity.
	 * @result as true or false
	 */
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
	/**
	 * @method editFeedback with id,Feedback entity.
	 * @result as Feedback list
	 */
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
	/**
	 * @method deleteFeedback with id
	 * @result as true or false
	 */
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
	/**
	 * @method view Feedback with id
	 * @result as Feedback list or null for id only
	 */
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
	/**
	 * @method view Feedback without args
	 * @result as Feedback list or null
	 */
	public List<Feedback> viewFeedbacks() {
		List<Feedback> allFeedbackList = mongoTemplate.findAll(Feedback.class);
		if (allFeedbackList != null) {
			logger.info("Feedback Existed List for All");
			return allFeedbackList;
		} else {
			return null;
		}
	}
    }
