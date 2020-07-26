package com.feedback.app.service;

import com.feedback.app.dao.RegistrationDaoImpl;
import com.feedback.app.entity.UserRegister;
import com.feedback.app.exception.BadRequestException;
import com.feedback.app.exception.EntryAlreadyExistException;
import com.feedback.app.model.request.LoginRequest;
import com.feedback.app.model.request.RegistrationRequest;
import com.feedback.app.model.response.FeedbackAppBaseResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
/**
 * The service test program helps to test registration model request to repository
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest{

    @InjectMocks
    @Spy
    private RegistrationDaoImpl registrationDao;

    @InjectMocks
    private RegistrationService registrationService;

    @InjectMocks
    private UserRegister userRegister;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
         }
    /**
     * @method test functional flow of addUser
     * @result as true
     */
    @Test
    public void addUserSuccessTest() throws Exception {
        RegistrationRequest request=new RegistrationRequest();
        request.setName("yamini");
        request.setEmployeeId(1430208);
        request.setPassword("yamini");
        request.setConfirmPassword("yamini");

        Mockito.doReturn(true).when(registrationDao).addUser(Mockito.anyObject());

        FeedbackAppBaseResponse feedbackAppBaseResponse = registrationService.addUser(request);
        Assert.assertEquals("1430208",feedbackAppBaseResponse.getResponseId());
    }
    /**
     * @method test functional flow of addUser
     * @result as false
     */
    @Test(expected=EntryAlreadyExistException.class)
    public void addUserFailTest() throws Exception {
        RegistrationRequest request=new RegistrationRequest();
        request.setName("yamini");
        request.setEmployeeId(1430208);
        request.setPassword("yamini");
        request.setConfirmPassword("yamini");

        Mockito.doReturn(false).when(registrationDao).addUser(Mockito.anyObject());
        FeedbackAppBaseResponse feedbackAppBaseResponse = registrationService.addUser(request);
        Assert.assertNotNull(feedbackAppBaseResponse);
    }
    /**
     * @method test functional flow of login
     * @result as true
     */
    @Test
    public void loginUserSuccessTest() throws Exception {
        LoginRequest request=new LoginRequest();
        request.setEmployeeId(1430208);
        request.setPassword("yamini");

        Mockito.doReturn(userRegister).when(registrationDao).loginUser(Mockito.anyObject());

        FeedbackAppBaseResponse feedbackAppBaseResponse = registrationService.login(request);
        Assert.assertEquals("1430208",feedbackAppBaseResponse.getResponseId());
    }
    /**
     * @method test functional flow of login
     * @result as false
     */
    @Test(expected= BadRequestException.class)
    public void loginUserFailTest() throws Exception {
        LoginRequest request=new LoginRequest();
        request.setEmployeeId(1430208);
        request.setPassword("yamini");

        Mockito.doReturn(null).when(registrationDao).loginUser(Mockito.anyObject());

        FeedbackAppBaseResponse feedbackAppBaseResponse = registrationService.login(request);
        Assert.assertEquals("1430208",feedbackAppBaseResponse.getResponseId());
    }

}