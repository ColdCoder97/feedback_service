package com.feedback.app.service;

import com.feedback.app.dao.FeedbackDaoImpl;
import com.feedback.app.entity.Feedback;
import com.feedback.app.entity.UserRegister;
import com.feedback.app.exception.BadRequestException;
import com.feedback.app.exception.DataStoreException;
import com.feedback.app.exception.EntryAlreadyExistException;
import com.feedback.app.model.request.FeedbackSubmission;
import com.feedback.app.model.request.RegistrationRequest;
import com.feedback.app.model.response.AllFeedBacksResponse;
import com.feedback.app.model.response.FeedbackAppBaseResponse;
import com.feedback.app.model.response.UpdatedFeedbackResponse;
import com.feedback.app.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.feedback.app.utils.Constants.*;

/*
 * Created by 1430208-Yamini S
 * Service Class for UserDetails.
 */
@Service
public class FeedbackService {

	@Autowired
	private FeedbackDaoImpl feedbackDao;

	public FeedbackAppBaseResponse insertFeedback(FeedbackSubmission request) throws Exception {
		FeedbackAppBaseResponse feedbackAppBaseResponse = new FeedbackAppBaseResponse();
		Feedback entity = new Feedback();
		entity.setName(request.getName());
		entity.setEmployeeId(request.getEmployeeId());
		entity.setCourseName(request.getCourseName());
		entity.setIsContentRelevant(request.getIsContentRelevant());
		entity.setContentHandsOn(request.getContentHandsOn());
		entity.setRatingContent(request.getRatingContent());
		entity.setRatingHandsOn(request.getRatingHandsOn());
		entity.setComment(request.getComment());
		//should get update at when records get update
		entity.setCreatedAt(System.currentTimeMillis());
		entity.setUpdatedAt(System.currentTimeMillis());
		boolean isEntryCreated = feedbackDao.insertFeedback(entity);
		if (isEntryCreated) {
			feedbackAppBaseResponse.setResponseId(String.valueOf(request.getEmployeeId()));
			feedbackAppBaseResponse.setMessage(Constants.FEEDBACK_ENTRY_CREATED);
			feedbackAppBaseResponse.setStatus(Constants.StatusCode.CREATED_SUCCESS);
		} else {
			throw new BadRequestException(NO_RECORDS_FOUND);
		}
		return feedbackAppBaseResponse;
	}

	public AllFeedBacksResponse viewFeedbacks(long employeeId) {
		AllFeedBacksResponse allFeedBacksResponse = new AllFeedBacksResponse();
		List<Feedback> feedbacksList = feedbackDao.viewFeedbacks(employeeId);
		allFeedBacksResponse.setStatus(Constants.StatusCode.SUCCESS);
		allFeedBacksResponse.setFeedbacks(feedbacksList);
		allFeedBacksResponse.setResponseId(String.valueOf(employeeId));
		if (feedbacksList != null) {
			allFeedBacksResponse.setMessage(RECORDS_FOUND);
		} else {
			allFeedBacksResponse.setMessage(NO_RECORDS_FOUND);
		}
		return allFeedBacksResponse;
	}

	public UpdatedFeedbackResponse editFeedbacks(String id, FeedbackSubmission feedback) {
		UpdatedFeedbackResponse updatedFeedbackResponse = new UpdatedFeedbackResponse();
		Feedback entityUpdate = new Feedback();
		entityUpdate.setCourseName(feedback.getCourseName());
		entityUpdate.setIsContentRelevant(feedback.getIsContentRelevant());
		entityUpdate.setContentHandsOn(feedback.getContentHandsOn());
		entityUpdate.setProficiencyLevel(feedback.getProficiencyLevel());
		entityUpdate.setRatingContent(feedback.getRatingContent());
		entityUpdate.setRatingHandsOn(feedback.getRatingHandsOn());
		entityUpdate.setComment(feedback.getComment());
		Feedback updatedList = feedbackDao.editFeedback(id, entityUpdate);
		if(updatedList !=null){
			updatedFeedbackResponse.setStatus(Constants.StatusCode.SUCCESS);
			updatedFeedbackResponse.setMessage(Constants.FEEDBACK_UPDATED);
			updatedFeedbackResponse.setFeedbacks(updatedList);
			updatedFeedbackResponse.setResponseId(String.valueOf(feedback.getEmployeeId()));
		}
		else{
			throw new BadRequestException(NO_RECORDS_FOUND);
		}
		return updatedFeedbackResponse;
	}

	public FeedbackAppBaseResponse deleteFeedback(String id) {
		FeedbackAppBaseResponse deleteFeedbackResponse = new FeedbackAppBaseResponse();
		boolean isDataRemoved = feedbackDao.deleteFeedback(id);
		deleteFeedbackResponse.setResponseId(id);
		if (isDataRemoved) {
			deleteFeedbackResponse.setMessage(RECORD_REMOVED);
			deleteFeedbackResponse.setStatus(Constants.StatusCode.SUCCESS);
		}
		else
		{
			deleteFeedbackResponse.setMessage(NO_RECORDS_FOUND);
			deleteFeedbackResponse.setStatus(Constants.StatusCode.RECORD_NOT_FOUND);
		}
		return deleteFeedbackResponse;
	}
}