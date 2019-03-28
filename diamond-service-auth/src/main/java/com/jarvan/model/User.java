package com.jarvan.model;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private String salt;

    private String sex;

    private Integer age;

    private Byte status;

    private String mobile;

    private String email;

    private Date createdTime;

    private Date updatedTime;

    private String wechat;

    private String qq;


}