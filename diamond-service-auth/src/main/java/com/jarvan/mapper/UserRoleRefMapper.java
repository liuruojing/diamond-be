package com.jarvan.mapper;

import com.jarvan.model.UserRoleRef;

public interface UserRoleRefMapper {
    int insert(UserRoleRef record);

    int insertSelective(UserRoleRef record);
}