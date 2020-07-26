package com.feedback.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
/**
 * The model request program helps to redirect api to User register repository
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegistrationRequest {
    private String name;
    private long employeeId;
    private String password;
    private String confirmPassword;
}
