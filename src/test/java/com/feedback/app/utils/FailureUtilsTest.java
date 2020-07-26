package com.feedback.app.utils;

import com.feedback.app.model.response.FeedBackFailureResponse;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FailureUtilsTest extends TestCase {

    @InjectMocks
    FailureUtils failureUtils;

    @InjectMocks
    FeedBackFailureResponse feedBackFailureResponse;
    /**
     * @method test functional flow of getFailureResponse
     * @result as true
     */
    @Test
    public void testGetFailureResponse() {
        FeedBackFailureResponse failureResponse = FailureUtils.getFailureResponse("1430208", "success", "exception", 200);
        Assert.assertNotNull(failureResponse);
    }
}