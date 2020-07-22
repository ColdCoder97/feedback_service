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

/*
 * Created by 1430208-Yamini S
 * Dao Class for register user request base.
 */
@Repository
@Slf4j
public class RegistrationDaoImpl implements RegistrationTrackerInterface {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

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