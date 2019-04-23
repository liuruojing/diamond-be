package com.jarvan.auth.service;

import com.jarvan.auth.entity.Permission;
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
public interface PermissionService extends IService<Permission> {
    /**
     * 将所属权限类型为ids的权限类型更新为0
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    void updatePerTypeToUncorrelated(List<Long> ids);
}
