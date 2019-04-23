package com.jarvan.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jarvan.auth.entity.Permission;
import com.jarvan.auth.mapper.PermissionMapper;
import com.jarvan.auth.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    public void updatePerTypeToUncorrelated(List<Long> ids){
        UpdateWrapper<Permission> wrapper = new UpdateWrapper<>();
        wrapper.set("per_type_id",0);
        wrapper.in("per_type_id",ids);
        update(wrapper);
    }

}
