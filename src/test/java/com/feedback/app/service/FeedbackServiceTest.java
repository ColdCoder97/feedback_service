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
import org.junit.Ignore;
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

import static com.feedback.app.utils.Constants.*;
import static org.mockito.Matchers.anyString;
/**
 * The service test program helps to test feedback model request to repository
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
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

    /**
     * @method test functional flow of insertFeedback
     * @result as true
     */
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
    /**
     * @method test functional flow of insertFeedback
     * @result as false
     */
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
    /**
     * @method test functional flow of viewFeedbacks
     * @result as true
     */
    @Test
    public void testViewFeedbacks() {
        Feedback feedback=new Feedback();
        feedback.setCourseName("java");
        List<Feedback> feedbackList=new ArrayList<>();
        feedbackList.add(feedback);
        AllFeedBacksResponse allFeedBacksResponse=new AllFeedBacksResponse();
        Mockito.doReturn(feedbackList).when(feedbackDao).viewFeedbacks("1430208");
        allFeedBacksResponse = feedbackService.viewFeedbacks("1430208");
        Assert.assertEquals(RECORDS_FOUND,allFeedBacksResponse.getMessage());

    }
    /**
     * @method test functional flow of viewFeedbacks
     * @result as false
     */
    @Test
    public void testViewFeedbacksFail() {
        Feedback feedback=new Feedback();
        List<Feedback> feedbackList=new ArrayList<>();
        AllFeedBacksResponse allFeedBacksResponse=new AllFeedBacksResponse();
        Mockito.doReturn(null).when(feedbackDao).viewFeedbacks("1430208");
        allFeedBacksResponse= feedbackService.viewFeedbacks("1430208");
        Assert.assertEquals(NO_RECORDS_FOUND,allFeedBacksResponse.getMessage());

    }

    /**
     * @method test functional flow of viewFeedbacks
     * @result as true
     */
    @Test
    public void testViewFeedbackAllSuccess() {
        Feedback feedback=new Feedback();
        feedback.setCourseName("java");
        List<Feedback> feedbackList=new ArrayList<>();
        feedbackList.add(feedback);
        AllFeedBacksResponse allFeedBacksResponse=new AllFeedBacksResponse();
        Mockito.doReturn(feedbackList).when(feedbackDao).viewFeedbacks();
        allFeedBacksResponse = feedbackService.viewFeedbacks();
        Assert.assertEquals(RECORDS_FOUND,allFeedBacksResponse.getMessage());

    }
    /**
     * @method test functional flow of viewFeedbacks
     * @result as false
     */
    @Test
    public void testViewFeedbackAllFail() {
        Feedback feedback=new Feedback();
        List<Feedback> feedbackList=new ArrayList<>();
        AllFeedBacksResponse allFeedBacksResponse=new AllFeedBacksResponse();
        Mockito.doReturn(null).when(feedbackDao).viewFeedbacks();
        allFeedBacksResponse= feedbackService.viewFeedbacks();
        Assert.assertEquals(NO_RECORDS_FOUND,allFeedBacksResponse.getMessage());

    }
    /**
     * @method test functional flow of editFeedback
     * @result as true
     */
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
        Mockito.doReturn(feedback).when(feedbackDao).editFeedback(anyString(),Mockito.anyObject());
        updatedFeedbackResponse = feedbackService.editFeedbacks("1430208",request);
        Assert.assertEquals("1430208",updatedFeedbackResponse.getResponseId());
    }
    /**
     * @method test functional flow of editFeedback
     * @result as false
     */
    @Test(expected= BadRequestException.class)
    public void testEditFeedbacksFail() throws Exception {
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
        Mockito.doReturn(null).when(feedbackDao).editFeedback(anyString(),Mockito.anyObject());
        updatedFeedbackResponse = feedbackService.editFeedbacks("1430208",request);
        Assert.assertEquals("1430208",updatedFeedbackResponse.getResponseId());
    }
    /**
     * @method test functional flow of deleteFeedback
     * @result as true
     */
    @Test
    public void testDeleteFeedbacksSuccesss() {

        Mockito.doReturn(true).when(feedbackDao).deleteFeedback(anyString());
        FeedbackAppBaseResponse deletefeedbackresponse = feedbackService.deleteFeedback("1430208");
        Assert.assertEquals(RECORD_REMOVED,deletefeedbackresponse.getMessage());

    }
    /**
     * @method test functional flow of deleteFeedback
     * @result as false
     */
    @Test
    public void testDeleteFeedbacksFail() {

        Mockito.doReturn(false).when(feedbackDao).deleteFeedback(anyString());
        FeedbackAppBaseResponse deletefeedbackresponse = feedbackService.deleteFeedback("1430208");
        Assert.assertEquals(NO_RECORDS_FOUND,deletefeedbackresponse.getMessage());

    }
}