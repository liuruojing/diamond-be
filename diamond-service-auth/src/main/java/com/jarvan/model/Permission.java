package com.jarvan.model;

import java.util.Date;

public class Permission {
    private Long id;

    private String perName;

    private Long perTypeId;

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

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }

    public Long getPerTypeId() {
        return perTypeId;
    }

    public void setPerTypeId(Long perTypeId) {
        this.perTypeId = perTypeId;
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