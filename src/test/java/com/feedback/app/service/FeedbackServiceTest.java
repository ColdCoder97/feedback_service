package com.feedback.app.service;

import com.feedback.app.dao.FeedbackDaoImpl;
import com.feedback.app.entity.Feedback;
import com.feedback.app.entity.UserRegister;
import com.feedback.app.exception.BadRequestException;
import com.feedback.app.model.request.FeedbackSubmission;
import com.feedback.app.model.request.RegistrationRequest;
import com.feedback.app.model.response.AllFeedBacksResponse;
import com.feedback.app.model.response.FeedbackAppBaseResponse;
import com.feedback.app.model.response.UpdatedFeedbackResponse;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.feedback.app.utils.Constants.NO_RECORDS_FOUND;
import static com.feedback.app.utils.Constants.RECORDS_FOUND;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceTest extends TestCase {


    @InjectMocks
    private  FeedbackService feedbackService;

    @InjectMocks
    @Spy
    private FeedbackDaoImpl feedbackDao;


    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void insertFeedbackSuccess() throws Exception {
        FeedbackSubmission request= new FeedbackSubmission();
        request.setName("yamini");
        request.setEmployeeId(1430208);
        request.setCourseName("java");
        request.setIsContentRelevant("yes");
        request.setContentHandsOn("yes");
        request.setRatingContent("5");
        request.setRatingHandsOn("5");
        request.setComment("Good");

        Mockito.doReturn(true).when(feedbackDao).insertFeedback(Mockito.anyObject());

        FeedbackAppBaseResponse feedbackAppBaseResponse = feedbackService.insertFeedback(request);
        Assert.assertEquals("1430208",feedbackAppBaseResponse.getResponseId());
    }
    @Test(expected= BadRequestException.class)
    public void insertFeedbackFail() throws Exception {
        FeedbackSubmission request= new FeedbackSubmission();
        request.setName("yamini");
        request.setEmployeeId(1430208);
        request.setCourseName("java");
        request.setIsContentRelevant("yes");
        request.setContentHandsOn("yes");
        request.setRatingContent("5");
        request.setRatingHandsOn("5");
        request.setComment("Good");

        Mockito.doReturn(false).when(feedbackDao).insertFeedback(Mockito.anyObject());

        FeedbackAppBaseResponse feedbackAppBaseResponse = feedbackService.insertFeedback(request);
        Assert.assertEquals("1430208",feedbackAppBaseResponse.getResponseId());
    }

    @Test
    public void testViewFeedbacks() {
        Feedback feedback=new Feedback();
        List<Feedback> feedbackList=new ArrayList<>();
        AllFeedBacksResponse allFeedBacksResponse=new AllFeedBacksResponse();
        Mockito.doReturn(feedbackList).when(feedbackDao).viewFeedbacks("1430208");
        allFeedBacksResponse = feedbackService.viewFeedbacks("1430208");
        Assert.assertEquals(RECORDS_FOUND,allFeedBacksResponse.getMessage());

    }
    @Test
    public void testViewFeedbacksFail() {
        AllFeedBacksResponse allFeedBacksResponse=new AllFeedBacksResponse();
        Mockito.doReturn(null).when(feedbackDao).viewFeedbacks("1430208");
        allFeedBacksResponse= feedbackService.viewFeedbacks("1430208");
        Assert.assertEquals(NO_RECORDS_FOUND,allFeedBacksResponse.getMessage());

    }
    @Test
    public void testEditFeedbacksSuccess() throws Exception {
        Feedback feedback=new Feedback();
        UpdatedFeedbackResponse updatedFeedbackResponse= new UpdatedFeedbackResponse();
        FeedbackSubmission request= new FeedbackSubmission();
        request.setName("yamini");
        request.setEmployeeId(1430208);
        request.setCourseName("java");
        request.setIsContentRelevant("yes");
        request.setContentHandsOn("yes");
        request.setRatingContent("5");
        request.setRatingHandsOn("5");
        request.setComment("Good");
        Mockito.doReturn(feedback).when(feedbackDao).editFeedback("1430208",Mockito.anyObject());
        updatedFeedbackResponse = feedbackService.editFeedbacks("1430208",request);
        Assert.assertEquals("1430208",updatedFeedbackResponse.getResponseId());
    }

    public void testEditFeedbacks() {
    }

    public void testDeleteFeedback() {
    }
}