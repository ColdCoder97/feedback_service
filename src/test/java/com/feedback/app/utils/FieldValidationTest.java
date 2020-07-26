package com.feedback.app.utils;

import com.feedback.app.exception.BadRequestException;
import com.feedback.app.exception.EntryAlreadyExistException;
import com.feedback.app.model.request.FeedbackSubmission;
import com.feedback.app.model.request.LoginRequest;
import com.feedback.app.model.request.RegistrationRequest;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FieldValidationTest{

     @InjectMocks
     FieldValidation fieldValidation;
    /**
     * @method test functional flow of registerFieldsValidation
     * @result as true
     */
    @Test
    public void testRegisterFieldsValidation() {
        RegistrationRequest request=new RegistrationRequest();
        request.setName("yamini");
        request.setEmployeeId(1430208);
        request.setPassword("yamini");
        request.setConfirmPassword("yamini");
        boolean isValidated = FieldValidation.registerFieldsValidation(request);
        Assert.assertEquals(true,isValidated);

    }
    /**
     * @method test functional flow of registerFieldsValidation
     * @result as false
     */
    @Test(expected= BadRequestException.class)
    public void testRegisterFieldsValidationfalse() {
        RegistrationRequest request=new RegistrationRequest();
        request.setName("yamini");
        request.setEmployeeId(1430208);
        request.setPassword("yamini");
        request.setConfirmPassword("soniya");
        boolean isValidated = FieldValidation.registerFieldsValidation(request);
        Assert.assertEquals(false,isValidated);

    }
    /**
     * @method test functional flow of loginFieldsValidation
     * @result as true
     */
    @Test
    public void testLoginFieldsValidation() {
        LoginRequest request=new LoginRequest();
        request.setEmployeeId(1430208);
        request.setPassword("yamini");
        boolean isValidated = FieldValidation.loginFieldsValidation(request);
        Assert.assertEquals(true,isValidated);
    }
    /**
     * @method test functional flow of loginFieldsValidation
     * @result as false
     */
    @Test(expected= BadRequestException.class)
    public void testLoginFieldsValidationfalse() {
        LoginRequest request=new LoginRequest();
        request.setEmployeeId(1430208);
        request.setPassword(null);
        boolean isValidated = FieldValidation.loginFieldsValidation(request);
        Assert.assertEquals(false,isValidated);
    }
    /**
     * @method test functional flow of feedbackFieldsValidation
     * @result as true
     */
    @Test
    public void testFeedbackFieldsValidation() {
        FeedbackSubmission request=new FeedbackSubmission();
        request.setEmployeeId(1430208);
        request.setName("yamini");
        request.setComment("Good");
        boolean isValidated = FieldValidation.feedbackFieldsValidation(request);
        Assert.assertEquals(true,isValidated);
    }
    /**
     * @method test functional flow of feedbackFieldsValidation
     * @result as false
     */
    @Test(expected= BadRequestException.class)
    public void testFeedbackFieldsValidationfalse() {
        FeedbackSubmission request=new FeedbackSubmission();
        request.setEmployeeId(1430208);
        request.setName("yamini");
        boolean isValidated = FieldValidation.feedbackFieldsValidation(request);
        Assert.assertEquals(false,isValidated);
    }
    /**
     * @method test functional flow of idFieldValidation
     * @result as true
     */
    @Test
    public void testIdFieldValidation() {
        boolean isValidated= FieldValidation.idFieldValidation("1430208");
        Assert.assertEquals(true,isValidated);
    }
    /**
     * @method test functional flow of idFieldValidation
     * @result as false
     */
    @Test(expected= BadRequestException.class)
    public void testIdFieldValidationfalse() {
        boolean isValidated= FieldValidation.idFieldValidation(null);
        Assert.assertEquals(false,isValidated);
    }


}