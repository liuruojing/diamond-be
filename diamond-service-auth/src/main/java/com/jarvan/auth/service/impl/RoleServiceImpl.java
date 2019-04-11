package com.jarvan.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jarvan.auth.dto.role.RoleDto;
import com.jarvan.auth.entity.Role;
import com.jarvan.auth.mapper.RoleMapper;
import com.jarvan.auth.service.RolePermissionRelationService;
import com.jarvan.auth.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jarvan.auth.service.UserRoleRelationService;
import com.jarvan.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Autowired
    private RolePermissionRelationService rolePermissionRelationService;

    @Override
    public IPage<RoleDto> showRoles(Integer pageSize, Integer pageNum) {

        return roleMapper.selectAll(new Page<RoleDto>(pageNum, pageSize));
    }

    @Override
    @Transactional
    public void delete(String ids) {
        List<Long> _ids = ConvertUtil.convert(ids, ",");
        if (_ids.size() == 1) {
            if(!checkRole(_ids.get(0))){
                throw new IllegalArgumentException("用户不存在");
            }
        }
        // 删除用户与角色之间的关联
        log.debug("删除角色-用户关联关系");
        userRoleRelationService.deleteByRoleIds(_ids);
        // 删除角色与权限之间的关联
        log.debug("删除角色-权限关联关系");
        rolePermissionRelationService.deleteByRoleIds(_ids);
        // 删除角色
        log.debug("删除角色关系");
        removeByIds(_ids);

    }

    @Override
    public boolean checkRole(Long roleId) {
        return getById(roleId) != null ? true : false;
    }
}
