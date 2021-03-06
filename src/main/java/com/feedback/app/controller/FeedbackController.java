package com.feedback.app.controller;

import com.feedback.app.exception.AuthenticationFailureException;
import com.feedback.app.exception.BadRequestException;;
import com.feedback.app.model.request.FeedbackSubmission;
import com.feedback.app.model.response.AllFeedBacksResponse;
import com.feedback.app.model.response.FeedBackFailureResponse;
import com.feedback.app.model.response.FeedbackAppBaseResponse;
import com.feedback.app.service.FeedbackService;
import com.feedback.app.utils.*;
import com.feedback.app.utils.Enum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * The Controller program implements an application feedback
 * logged in user adds feedback
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/api")
public class FeedbackController {
	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackService feedbackService;

	/**
	 * @method insertFeedback with FeedbackSubmission request.
	 * @exception AuthenticationFailureException invalid user enters input
	 * @exception BadRequestException invalid data as input
	 * @result adds valid data
	 */
	@PostMapping( "/v1/addFeedback")
	public ResponseEntity insertFeedback(@RequestBody FeedbackSubmission request,@RequestHeader("x-client-id") String clientName,@RequestHeader("x-client-key") String clientValue) {
		FeedbackAppBaseResponse responseAddFeedback=new FeedbackAppBaseResponse();
		try {
			logger.info("Validating client.....");
			boolean validClient = ClientsAuthValidator.validateClient(clientName, clientValue);
			if(validClient){
			boolean isValidRequest = FieldValidation.feedbackFieldsValidation(request);
			logger.info("FeedBack insertion request received for employee id:" + request.getEmployeeId());
			if(isValidRequest){
				responseAddFeedback = feedbackService.insertFeedback(request);
			}
			}
		}
		catch (AuthenticationFailureException exception) {
			logger.error(" Authentication Failure : "+exception.getMessage());
			FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(clientName),
					exception.getMessage(), Enum.RequestStatus.AUTHENTICATION_FAILED.name(), Constants.StatusCode.AUTHENTICATION_FAILURE);
			return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.FORBIDDEN);
		}catch (BadRequestException exception) {
			logger.error("Getting Bad Request Exception: "+exception.getMessage()+"...also EmployeeID,Name,Comments should not be Null");
			FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(request.getEmployeeId()),
					exception.getMessage(), Enum.RequestStatus.VALIDATION_FAILURE.name(), Constants.StatusCode.BAD_REQUEST);
			return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			logger.error("Internal Server Exception: " + e);
			FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(request.getEmployeeId()),
					e.getMessage(), Enum.RequestStatus.FAILURE.name(), Constants.StatusCode.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("FeedBack Insertion completed for EmployeeId" + request.getEmployeeId());
		return new ResponseEntity<FeedbackAppBaseResponse>(responseAddFeedback, HttpStatus.ACCEPTED);

	}

	/**
	 * @method getFeedback with id request.
	 * @exception Exception server not connected
	 * @result view valid data for id only
	 */
	@GetMapping("/v1/feedbacks/{id}")
	public ResponseEntity getFeedback(@PathVariable String id) {

		AllFeedBacksResponse allFeedBacksResponse=new AllFeedBacksResponse();
		try {
			logger.info(" View request received from id:" +id);
			allFeedBacksResponse = feedbackService.viewFeedbacks(id);
		}
		catch (Exception e) {
			logger.error("Internal Server Exception: " + e);
			FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(id,
					e.getMessage(), Enum.RequestStatus.FAILURE.name(), Constants.StatusCode.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Feedback view request completed for id" + id);
		return new ResponseEntity<AllFeedBacksResponse>(allFeedBacksResponse, HttpStatus.ACCEPTED);

	}
	/**
	 * @method getFeedback with  request.
	 * @exception Exception server not connected
	 * @result views all data valid
	 */
	@GetMapping("/v1/feedbacks")
	public ResponseEntity getFeedback() {

		AllFeedBacksResponse allFeedBacksResponse=new AllFeedBacksResponse();
		try {
			logger.info(" View request received for All");
			allFeedBacksResponse = feedbackService.viewFeedbacks();
		}
		catch (Exception e) {
			logger.error("Internal Server Exception: " + e);
			FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse("AllUser",
					e.getMessage(), Enum.RequestStatus.FAILURE.name(), Constants.StatusCode.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Feedback view request completed for All");
		return new ResponseEntity<AllFeedBacksResponse>(allFeedBacksResponse, HttpStatus.ACCEPTED);

	}
	/**
	 * @method editFeedback with id,FeedbackSubmission request.
	 * @exception BadRequestException invalid data as input
	 * @exception Exception server not connected
	 * @result updates existed valid data as per id
	 */
	@PutMapping("/v1/feedback/{id}")
	public ResponseEntity editFeedback(@PathVariable String id, @RequestBody FeedbackSubmission feedback) {
		FeedbackAppBaseResponse responseUpdateFeedback = new FeedbackAppBaseResponse();
		try {
			logger.info(" Edit request received from employee id:" + id);
			boolean isValidRequest = FieldValidation.feedbackFieldsValidation(feedback);
			if (isValidRequest) {
				responseUpdateFeedback = feedbackService.editFeedbacks(id, feedback);
			}
		}
		catch (BadRequestException exception) {
			logger.error("Getting Bad Request Exception"+exception.getMessage()+"EmployeeID,Name,Comments should not be Null");
			FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(feedback.getEmployeeId()),
					exception.getMessage(), Enum.RequestStatus.FAILURE.name(), Constants.StatusCode.RECORD_NOT_FOUND);
			return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			logger.error("Internal Server Exception: " + e);
			FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(feedback.getEmployeeId()),
					e.getMessage(), Enum.RequestStatus.FAILURE.name(), Constants.StatusCode.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Feedback updation completed for EmployeeId");
		return new ResponseEntity<FeedbackAppBaseResponse>(responseUpdateFeedback, HttpStatus.ACCEPTED);
	}
	/**
	 * @method deleteFeedback with id
	 * @exception BadRequestException invalid data as input
	 * @exception Exception server not connected
	 * @result removes existed data as per id
	 */
	@DeleteMapping("/v1/feedback/{id}")
	public ResponseEntity deleteFeedback(@PathVariable String id) {
		FeedbackAppBaseResponse responseRemoveFeedback = new FeedbackAppBaseResponse();
		try {
			logger.info(" Delete request received from employee id:" + id);
			boolean isValidRequest = FieldValidation.idFieldValidation(id);
			if (isValidRequest) {
				responseRemoveFeedback = feedbackService.deleteFeedback(id);
			}
		} catch (BadRequestException exception) {
			logger.error("Getting Bad Request Exception"+exception.getMessage()+"EmployeeID,Name,Comments should not be Null");
			FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(id,
					exception.getMessage(), Enum.RequestStatus.VALIDATION_FAILURE.name(), Constants.StatusCode.BAD_REQUEST);
			return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			logger.error("Internal Server Exception: " + e);
			FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(id,
					e.getMessage(), Enum.RequestStatus.FAILURE.name(), Constants.StatusCode.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Feedback field Deletion completed");
		return new ResponseEntity<FeedbackAppBaseResponse>(responseRemoveFeedback, HttpStatus.ACCEPTED);
	}
}
