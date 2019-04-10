package com.jarvan.auth.service;

import com.jarvan.auth.entity.RolePermissionRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
public interface RolePermissionRelationService extends IService<RolePermissionRelation> {

    void deleteByRoleIds(List<Long> ids);

    void deleteByPermissionIds(List<Long> ids);
}
