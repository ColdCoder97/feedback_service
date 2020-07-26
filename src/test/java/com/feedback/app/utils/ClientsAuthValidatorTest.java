package com.feedback.app.utils;

import com.feedback.app.exception.AuthenticationFailureException;
import com.feedback.app.exception.BadRequestException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientsAuthValidatorTest extends TestCase {

    @InjectMocks
    ClientsAuthValidator clientsAuthValidator;
    /**
     * @method test functional flow of validateClient
     * @result as true
     */
    @Test
    public void testValidateClientSuccess() {
        boolean isValidated= ClientsAuthValidator.validateClient("tcs", "feedback_yamini");
        Assert.assertEquals(true,isValidated);
    }
    /**
     * @method test functional flow of validateClient
     * @result as false
     */
    @Test
    public void testValidateClientFail() {
        boolean isValidated= ClientsAuthValidator.validateClient(null, "yamini");
        Assert.assertNotNull(isValidated);
    }
    /**
     * @method test functional flow of validateClient
     * @result as exception
     */
    @Test(expected= AuthenticationFailureException.class)
    public void testValidateClientException() {
        boolean isValidated= ClientsAuthValidator.validateClient("yamini", "yamini");
        Assert.assertNotNull(isValidated);
    }
}