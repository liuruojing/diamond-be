package com.jarvan.mapper;

import com.jarvan.model.PermissionType;

public interface PermissionTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PermissionType record);

    int insertSelective(PermissionType record);

    PermissionType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PermissionType record);

    int updateByPrimaryKey(PermissionType record);
}