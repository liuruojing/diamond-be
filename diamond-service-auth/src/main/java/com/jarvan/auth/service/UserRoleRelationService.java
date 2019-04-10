package com.jarvan.auth.service;

import com.jarvan.auth.entity.UserRoleRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
public interface UserRoleRelationService extends IService<UserRoleRelation> {
    /**
     * 删除用户的授予角色关联管理
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    void deleteByUserIds(List<Long> ids);

    void deleteByRoleIds(List<Long> ids);

    List<UserRoleRelation> selectRoleByUserId(Long userId);

    void authorization(Long userId, String roleIds);
}
