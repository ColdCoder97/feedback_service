package com.feedback.app.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegistrationRequest {
    private String name;
    private long employeeId;
    private String password;
    private String confirmPassword;
}
