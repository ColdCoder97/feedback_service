package com.feedback.app.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Created by 1430208-Yamini S
 * Entity Class for User.
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
