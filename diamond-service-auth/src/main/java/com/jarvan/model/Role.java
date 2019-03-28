package com.jarvan.model;

import java.util.Date;

public class Role {
    private Long id;

    private String roleName;

    private Date createdTime;

    private Date updatedTime;

    private Long cretedUserId;

    private Long updatedUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getCretedUserId() {
        return cretedUserId;
    }

    public void setCretedUserId(Long cretedUserId) {
        this.cretedUserId = cretedUserId;
    }

    public Long getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(Long updatedUserId) {
        this.updatedUserId = updatedUserId;
    }
}