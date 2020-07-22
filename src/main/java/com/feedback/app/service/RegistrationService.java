package com.feedback.app.service;

import com.feedback.app.dao.RegistrationDaoImpl;
import com.feedback.app.entity.UserRegister;
import com.feedback.app.exception.BadRequestException;
import com.feedback.app.exception.EntryAlreadyExistException;
import com.feedback.app.model.request.LoginRequest;
import com.feedback.app.model.request.RegistrationRequest;
import com.feedback.app.model.response.FeedBackFailureResponse;
import com.feedback.app.model.response.FeedbackAppBaseResponse;
import com.feedback.app.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.feedback.app.utils.Constants.RECORD_ALREADY_EXIST;
import static com.feedback.app.utils.Constants.VALIDATION_FAILURE;

/*
 * Created by 1430208-Yamini S
 * Service Class for Login.
 */
@Service
public class RegistrationService {

    @Autowired
    private RegistrationDaoImpl registrationDaoImpl;

    public FeedbackAppBaseResponse addUser(RegistrationRequest registration) throws Exception {
        FeedbackAppBaseResponse feedbackAppBaseResponse=new FeedbackAppBaseResponse();
        UserRegister entity=new UserRegister();
        entity.setName(registration.getName());
        entity.setEmployeeId(registration.getEmployeeId());
        entity.setPassword(registration.getPassword());
        entity.setConfirmPassword(registration.getConfirmPassword());
        entity.setCreatedAt(System.currentTimeMillis());
        //should get update at when records get update
        entity.setUpdatedAt(System.currentTimeMillis());
        boolean isEntryCreated = registrationDaoImpl.addUser(entity);
            if(isEntryCreated) {
                feedbackAppBaseResponse.setResponseId(String.valueOf(registration.getEmployeeId()));
                feedbackAppBaseResponse.setMessage(Constants.REGISTRATION_COMPLETED);
                feedbackAppBaseResponse.setStatus(Constants.StatusCode.CREATED_SUCCESS);
            }
           else{

               throw new EntryAlreadyExistException(RECORD_ALREADY_EXIST);
            }
           return feedbackAppBaseResponse;
    }

    public FeedbackAppBaseResponse login(LoginRequest login) throws Exception {
        FeedbackAppBaseResponse feedbackAppBaseResponse = new FeedbackAppBaseResponse();
        UserRegister isLoginResponse = registrationDaoImpl.loginUser(login);
         if (isLoginResponse != null) {
            feedbackAppBaseResponse.setResponseId(String.valueOf(login.getEmployeeId()));
            feedbackAppBaseResponse.setMessage(Constants.LOGIN_SUCCESS);
            feedbackAppBaseResponse.setStatus(Constants.StatusCode.SUCCESS);
        } else {
            throw new BadRequestException(VALIDATION_FAILURE);
        }
        return feedbackAppBaseResponse;
    }
    }

