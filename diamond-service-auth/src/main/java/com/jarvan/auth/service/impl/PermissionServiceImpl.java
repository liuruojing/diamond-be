package com.jarvan.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jarvan.auth.dto.permission.AddPermissionDto;
import com.jarvan.auth.entity.Permission;
import com.jarvan.auth.entity.PermissionType;
import com.jarvan.auth.mapper.PermissionMapper;
import com.jarvan.auth.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jarvan.auth.service.PermissionTypeService;
import com.jarvan.util.ClassUtil;
import com.jarvan.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class PermissionServiceImpl extends
        ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionTypeService permissionTypeService;

    public void updatePerTypeToUncorrelated(List<Long> ids) {
        UpdateWrapper<Permission> wrapper = new UpdateWrapper<>();
        wrapper.set("per_type_id", 0);
        wrapper.in("per_type_id", ids);
        update(wrapper);
    }

    @Override
    public List<Permission> selectByTypeId(Long id) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("per_type_id", id);
        wrapper.eq("is_primary", 0);
        return list(wrapper);
    }

    @Override
    public void deleteByPerTypeId(List<Long> ids) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.in("per_type_id", ids);
        wrapper.eq("is_primary", 0);
        remove(wrapper);

    }

    @Override
    public Permission save(AddPermissionDto permission) {
        // 不是游离权限且权限类型不存在，抛出异常
        if (permission.getPerTypeId() != 0 && !permissionTypeService
                .checkPermissionType(permission.getPerTypeId())) {
            throw new IllegalArgumentException("权限类型不存在");
        }
        Permission _permission = ClassUtil.transfer(permission,
                Permission.class);
        // 置为非内置权限
        _permission.setIsPrimary(0);
        // todo 设置创建人
        _permission.setCreatedTime(LocalDateTime.now());
        save(_permission);
        return _permission;
    }

    @Override
    public void delete(String ids) {
        List<Long> _ids = ConvertUtil.convert(ids, ",");
        if (_ids.size() == 1) {
            if (!checkPermission(_ids.get(0))) {
                throw new IllegalArgumentException("权限不存在");
            }
        }
        removeByIds(_ids);
    }

    public boolean checkPermission(Long id) {
        return getById(id) != null ? true : false;
    }

    @Override
    public void updatePerTypeId(Long id, Long perTypeId) {
        if (!checkPermission(id)) {
            throw new IllegalArgumentException("权限不存在");
        }
        //不是游离权限且指定权限类型不存在
        if (perTypeId != 0
                && !permissionTypeService.checkPermissionType(perTypeId)) {
            throw new IllegalArgumentException("权限类型不存在");
        }
        // 更新权限的所属类型
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.set("per_type_id", perTypeId);
        wrapper.eq("id", id);
        update(wrapper);
    }

}
