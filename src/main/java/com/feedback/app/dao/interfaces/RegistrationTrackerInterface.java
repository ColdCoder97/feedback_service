package com.feedback.app.dao.interfaces;

import com.feedback.app.entity.Feedback;
import com.feedback.app.entity.UserRegister;
import com.feedback.app.model.request.LoginRequest;

public interface RegistrationTrackerInterface {
    public boolean addUser(UserRegister register) throws Exception;
    public UserRegister loginUser(LoginRequest loginRequest);

}
