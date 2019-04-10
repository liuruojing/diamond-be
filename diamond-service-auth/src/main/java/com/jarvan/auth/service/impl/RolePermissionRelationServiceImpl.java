package com.jarvan.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jarvan.auth.entity.RolePermissionRelation;
import com.jarvan.auth.mapper.RolePermissionRelationMapper;
import com.jarvan.auth.service.RolePermissionRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@Service
public class RolePermissionRelationServiceImpl extends ServiceImpl<RolePermissionRelationMapper, RolePermissionRelation> implements RolePermissionRelationService {

    @Override
    public void deleteByRoleIds(List<Long> ids) {
        QueryWrapper<RolePermissionRelation> wrapper = new QueryWrapper<>();
        wrapper = wrapper.in("role_id",ids);
        remove(wrapper);
    }

    @Override
    public void deleteByPermissionIds(List<Long> ids) {
        QueryWrapper<RolePermissionRelation> wrapper = new QueryWrapper<>();
        wrapper = wrapper.in("permission_id",ids);
        remove(wrapper);
    }

}
