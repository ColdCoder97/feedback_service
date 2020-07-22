package com.feedback.app.utils;

import com.feedback.app.exception.BadRequestException;
import com.feedback.app.model.request.FeedbackSubmission;
import com.feedback.app.model.request.LoginRequest;
import com.feedback.app.model.request.RegistrationRequest;

public class FieldValidation {
    public static boolean registerFieldsValidation(RegistrationRequest request){

        if(request.getEmployeeId()>0&& request.getConfirmPassword().equals(request.getPassword())&&request.getPassword()!=null&&request.getName()!=null){
          return true;
        }
        else{
            throw new BadRequestException(Constants.EMPLOYEE_FIELD_FAILURE);
        }
    }
    public static boolean loginFieldsValidation(LoginRequest request){

        if(request.getEmployeeId()>0&&request.getPassword()!=null){
            return true;
        }
        else{
            throw new BadRequestException(Constants.FIELD_VALIDATION_FAILURE);
        }
    }
    public static boolean feedbackFieldsValidation(FeedbackSubmission request){

        if(request.getEmployeeId()>0&&request.getName()!=null&&request.getName().length()>0&&request.getComment()!=null&&request.getComment().length()>0){
            return true;
        }
        else{
            throw new BadRequestException(Constants.FIELD_VALIDATION_FAILURE);
        }
    }
    public static boolean idFieldValidation(String id){

        if(id!=null){
            return true;
        }
        else{
            throw new BadRequestException(Constants.FIELD_VALIDATION_FAILURE);
        }
    }
}
