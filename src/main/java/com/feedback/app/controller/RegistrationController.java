package com.feedback.app.controller;

import com.feedback.app.exception.BadRequestException;
import com.feedback.app.exception.EntryAlreadyExistException;
import com.feedback.app.model.request.LoginRequest;
import com.feedback.app.model.request.RegistrationRequest;
import com.feedback.app.model.response.FeedBackFailureResponse;
import com.feedback.app.model.response.FeedbackAppBaseResponse;
import com.feedback.app.service.RegistrationService;
import com.feedback.app.utils.Constants;
import com.feedback.app.utils.Enum;
import com.feedback.app.utils.FailureUtils;
import com.feedback.app.utils.FieldValidation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* Created by 1430208-Yamini S
* Controller Class for login api which performs operations adding and login user.
*/
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/api")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

	@PostMapping( "/v1/register")
    public ResponseEntity addUser(@RequestBody RegistrationRequest request) {
        FeedbackAppBaseResponse registerSuccessResponse=new FeedbackAppBaseResponse();
        try {
            boolean isValidRequest = FieldValidation.registerFieldsValidation(request);
            logger.info("Registration request received for employee id:" + request.getEmployeeId());
            if(isValidRequest){
                registerSuccessResponse = registrationService.addUser(request);
            }
        } catch (BadRequestException exception) {
            logger.error("Getting Bad Request Exception"+exception.getMessage());
            FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(request.getEmployeeId()),
                    exception.getMessage(), Enum.RequestStatus.VALIDATION_FAILURE.name(), Constants.StatusCode.BAD_REQUEST);
            return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.BAD_REQUEST);
        }
        catch (EntryAlreadyExistException exception) {
            logger.error("Getting Bad Request Exception"+exception.getMessage());
            FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(request.getEmployeeId()),
                    exception.getMessage(), Enum.RequestStatus.FAILURE.name(), Constants.StatusCode.BAD_REQUEST_RECORD_ALREADY_EXIST);
            return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.CONFLICT);
        }
        catch (Exception e) {
          logger.error("Internal Server Exception: " + e);
            FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(request.getEmployeeId()),
                    e.getMessage(), Enum.RequestStatus.FAILURE.name(), Constants.StatusCode.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Registration completed for EmployeeId" + request.getEmployeeId());
        return new ResponseEntity<FeedbackAppBaseResponse>(registerSuccessResponse, HttpStatus.ACCEPTED);

    }

    @PostMapping( "/v1/login")
    public ResponseEntity userLogin(@RequestBody LoginRequest login) {
        FeedbackAppBaseResponse loginSuccessResponse=new FeedbackAppBaseResponse();
        try
        {	 boolean isValidRequest = FieldValidation.loginFieldsValidation(login);
            logger.info("Login request received for employee id:" + login.getEmployeeId());
            if(isValidRequest){
                loginSuccessResponse=registrationService.login(login);
            }
        }catch (BadRequestException exception) {
            logger.error("Getting Bad Request Exception:"+exception.getMessage()+"for"+login.getEmployeeId());
            FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(login.getEmployeeId()),
                    exception.getMessage(), Enum.RequestStatus.AUTHENTICATION_FAILED.name(), Constants.StatusCode.BAD_REQUEST);
            return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            logger.error("Internal Server Exception: " + e);
            FeedBackFailureResponse feedBackFailureResponse = FailureUtils.getFailureResponse(String.valueOf(login.getEmployeeId()),
                    e.getMessage(), Enum.RequestStatus.FAILURE.name(), Constants.StatusCode.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<FeedBackFailureResponse>(feedBackFailureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Login Success for EmployeeId" + login.getEmployeeId());
        return new ResponseEntity<FeedbackAppBaseResponse>(loginSuccessResponse, HttpStatus.ACCEPTED);

    }
}
