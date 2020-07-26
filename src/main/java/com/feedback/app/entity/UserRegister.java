package com.feedback.app.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The entity program helps to implements an application registration
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
@Document
@Data
public class UserRegister {

    private String name;
    private long employeeId;
    private String password;
    private String confirmPassword;
    private long createdAt; //millisecond storing
    private long updatedAt;

}
