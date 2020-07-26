package com.feedback.app.service;

import com.feedback.app.dao.RegistrationDaoImpl;
import com.feedback.app.entity.UserRegister;
import com.feedback.app.exception.BadRequestException;
import com.feedback.app.exception.EntryAlreadyExistException;
import com.feedback.app.model.request.LoginRequest;
import com.feedback.app.model.request.RegistrationRequest;
import com.feedback.app.model.response.FeedbackAppBaseResponse;
import com.feedback.app.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.feedback.app.utils.Constants.RECORD_ALREADY_EXIST;
import static com.feedback.app.utils.Constants.VALIDATION_FAILURE;

/**
 * The service program helps to redirect registration model request to repository
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
@Service
public class RegistrationService {

    @Autowired
    private RegistrationDaoImpl registrationDaoImpl;
    /**
     * @method addUser with RegistrationRequest
     * @exception EntryAlreadyExistException data already exists
     * @result FeedbackAppBaseResponse as response entity
     */
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
    /**
     * @method login with LoginRequest
     * @exception BadRequestException invalid data as input
     * @result FeedbackAppBaseResponse as response entity
     */
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

