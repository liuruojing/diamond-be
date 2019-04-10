package com.jarvan.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jarvan.auth.dto.role.RoleDto;
import com.jarvan.auth.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  角色管理服务类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
public interface RoleService extends IService<Role> {
    /**
     * 分页显示角色列表.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    IPage<RoleDto> showRoles(Integer pageSize, Integer pageNum);

    void delete(String ids);

    boolean checkRole(Long roleId);
}
