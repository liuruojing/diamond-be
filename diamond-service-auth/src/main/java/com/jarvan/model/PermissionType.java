package com.jarvan.model;

import java.util.Date;

public class PermissionType {
    private Long id;

    private String perTypeName;

    private Date createdTime;

    private Long createdUserId;

    private Date updatedTime;

    private Long updatedUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerTypeName() {
        return perTypeName;
    }

    public void setPerTypeName(String perTypeName) {
        this.perTypeName = perTypeName == null ? null : perTypeName.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(Long createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(Long updatedUserId) {
        this.updatedUserId = updatedUserId;
    }
}