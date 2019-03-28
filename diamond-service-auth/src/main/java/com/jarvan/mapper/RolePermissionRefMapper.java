package com.jarvan.mapper;

import com.jarvan.model.RolePermissionRef;

public interface RolePermissionRefMapper {
    int insert(RolePermissionRef record);

    int insertSelective(RolePermissionRef record);
}