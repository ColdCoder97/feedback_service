package com.feedback.app.dao.interfaces;

import com.feedback.app.entity.UserRegister;
import com.feedback.app.model.request.LoginRequest;

/**
 * The interface program helps to implements an application registration
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */

public interface RegistrationTrackerInterface {
    public boolean addUser(UserRegister register) throws Exception;
    public UserRegister loginUser(LoginRequest loginRequest);

}
