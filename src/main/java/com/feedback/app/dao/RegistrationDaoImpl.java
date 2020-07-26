package com.feedback.app.dao;

import com.feedback.app.dao.interfaces.RegistrationTrackerInterface;
import com.feedback.app.entity.UserRegister;
import com.feedback.app.model.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * The repository program helps to implements an application registration
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */

@Repository
@Slf4j
public class RegistrationDaoImpl implements RegistrationTrackerInterface {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * @method adds User UserRegister as args
     * @result as true or false
     */
    @Override
    public boolean addUser(UserRegister userRegister) throws IOException {
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeId").is(userRegister.getEmployeeId()));
        UserRegister isAlreadyCreated = mongoTemplate.findOne(query, UserRegister.class);
        if (isAlreadyCreated != null) {
            logger.info("User Already Exists");
            return false;
        } else {
            mongoTemplate.save(userRegister);
            return true;
        }
    }
    /**
     * @method login User LoginRequest as args
     * @result as LoginRequest list or null
     */
    @Override
    public UserRegister loginUser(LoginRequest loginRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeId").is(loginRequest.getEmployeeId()).andOperator(Criteria.where("password").is(loginRequest.getPassword())));
        UserRegister isRegistered = mongoTemplate.findOne(query, UserRegister.class);
        if (isRegistered != null) {
            logger.info("Login request validated for Employee:"+loginRequest.getEmployeeId());
            return isRegistered;
        } else {
            logger.info("Register before login,registration is not done");
            return null;
        }
    }
}