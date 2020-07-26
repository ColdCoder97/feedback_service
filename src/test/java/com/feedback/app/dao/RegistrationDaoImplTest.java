package com.feedback.app.dao;

import com.feedback.app.entity.UserRegister;
import com.feedback.app.model.request.LoginRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
/**
 * The repository test program helps to test an application registration
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */

@RunWith(MockitoJUnitRunner.class)
public class RegistrationDaoImplTest {


    @InjectMocks
    @Spy
    RegistrationDaoImpl registrationDao;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
    }
    /**
     * @method test functional flow of addUser
     * @result as true
     */
    @Test (expected= NullPointerException.class)
    public void testAddUser() throws IOException {
        MongoTemplate mongoTemplate = Mockito.mock(MongoTemplate.class);
        UserRegister userRegister=new UserRegister();
        userRegister.setEmployeeId(1430208);
        userRegister.setPassword("yamini");
        userRegister.setConfirmPassword("yamini");
        userRegister.setName("yamini");
        Mockito.doReturn(userRegister).when(mongoTemplate).findOne(Mockito.anyObject(), Mockito.anyObject());
        boolean isAdded=registrationDao.addUser(userRegister);
        Assert.assertEquals(true,isAdded);
    }
    /**
     * @method test functional flow of loginUser
     * @result as true
     */
    @Test (expected= NullPointerException.class)
    public void testloginUser() throws IOException {
        MongoTemplate mongoTemplate = Mockito.mock(MongoTemplate.class);
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setEmployeeId(1430208);
        loginRequest.setPassword("yamini");
        Mockito.doReturn(loginRequest).when(mongoTemplate).findOne(Mockito.anyObject(), Mockito.anyObject());
        UserRegister isAdded=registrationDao.loginUser(loginRequest);
        Assert.assertNotNull(isAdded);
    }

}