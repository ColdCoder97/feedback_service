package com.feedback.app.utils;
/**
 * The security program helps to get secured input data
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
public class Constants {
    public final static String EMPLOYEE_FIELD_FAILURE = "EmployeeID should be Valid Please Check";
    public final static String REGISTRATION_COMPLETED = "Registration Entry Created Successfully";
    public final static String RECORD_ALREADY_EXIST = "Record Already Existed";
    public final static String LOGIN_SUCCESS = "User Logged in Success";
    public final static String VALIDATION_FAILURE="EmployeeID and Password should be registered before login";
    public final static String FIELD_VALIDATION_FAILURE="Input fields should be valid";
    public final static String FEEDBACK_ENTRY_CREATED="Feedback Created Successfully";
    public final static String NO_RECORDS_FOUND="Feedback record not found ";
    public final static String RECORDS_FOUND="Feedback records found ";
    public final static String FEEDBACK_UPDATED="Feedback Updated Successfully";
    public final static String RECORD_REMOVED="Feedback Record Removed Successfully";
    public final static String AUTHENTICATION_FAILED="Authentication Header should be valid";

    public class StatusCode{
        public static final int BAD_REQUEST=400;
        public static final int INTERNAL_SERVER_ERROR=500;
        public static final int BAD_REQUEST_RECORD_ALREADY_EXIST=409;
        public static final int CREATED_SUCCESS=201;
        public static final int SUCCESS=200;
        public static final int RECORD_NOT_FOUND=404;
        public static final int AUTHENTICATION_FAILURE=401 ;

    }
}
