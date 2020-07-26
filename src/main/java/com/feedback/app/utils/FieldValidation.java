package com.feedback.app.utils;

import com.feedback.app.exception.BadRequestException;
import com.feedback.app.model.request.FeedbackSubmission;
import com.feedback.app.model.request.LoginRequest;
import com.feedback.app.model.request.RegistrationRequest;

/**
 * The validation program helps to validate input data
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
public class FieldValidation {
    /**
     * @method registerFieldsValidation with RegistrationRequest as args
     * @exception BadRequestException invalid data as input
     * @result true
     */
    public static boolean registerFieldsValidation(RegistrationRequest request){

        if(request.getEmployeeId()>0&& request.getConfirmPassword().equals(request.getPassword())&&request.getPassword()!=null&&request.getName()!=null){
          return true;
        }
        else{
            throw new BadRequestException(Constants.EMPLOYEE_FIELD_FAILURE);
        }
    }
    /**
     * @method loginFieldsValidation with LoginRequest as args
     * @exception BadRequestException invalid data as input
     * @result true
     */
    public static boolean loginFieldsValidation(LoginRequest request){

        if(request.getEmployeeId()>0&&request.getPassword()!=null){
            return true;
        }
        else{
            throw new BadRequestException(Constants.FIELD_VALIDATION_FAILURE);
        }
    }
    /**
     * @method feedbackFieldsValidation with FeedbackSubmission as args
     * @exception BadRequestException invalid data as input
     * @result true
     */
    public static boolean feedbackFieldsValidation(FeedbackSubmission request){

        if(request.getEmployeeId()>0&&request.getName()!=null&&request.getName().length()>0&&request.getComment()!=null&&request.getComment().length()>0){
            return true;
        }
        else{
            throw new BadRequestException(Constants.FIELD_VALIDATION_FAILURE);
        }
    }
    /**
     * @method idFieldValidation with id as args
     * @exception BadRequestException invalid data as input
     * @result true
     */
    public static boolean idFieldValidation(String id){

        if(id!=null){
            return true;
        }
        else{
            throw new BadRequestException(Constants.FIELD_VALIDATION_FAILURE);
        }
    }
}
