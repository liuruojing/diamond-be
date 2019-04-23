package com.jarvan.auth.service;

import com.jarvan.auth.dto.permission.AddPermissionDto;
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
     * 将相应权限类型的权限类型更新为0，即置为游离权限.
     *
     * @param ids 权限类型的集合
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    void updatePerTypeToUncorrelated(List<Long> ids);

    /**
     * 根据权限类型来获取从属权限.
     *
     * @param id 权限类型
     * @return 权限集合
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    List<Permission> selectByTypeId(Long id);

    /**
     * 删除掉对应权限类型的权限
     *
     * @param ids 权限类型集合
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    void deleteByPerTypeId(List<Long> ids);

    /**
     * 新增权限
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    Permission save(AddPermissionDto permission);

    /**
     * 删除指定权限.
     *
     * @param ids 权限id们
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    void delete(String ids);

    boolean checkPermission(Long id);

    /**
     * 将权限id为#{id}的权限类型修改为#{perTypeId}
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    void updatePerTypeId(Long id, Long perTypeId);
}
