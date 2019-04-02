package com.jarvan.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jarvan.auth.entity.User;
import com.jarvan.auth.mapper.UserMapper;
import com.jarvan.auth.mapper.UserRoleRelationMapper;
import com.jarvan.auth.service.UserRoleRelationService;
import com.jarvan.auth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jarvan.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 用户管理serviceImpl
 * </p>
 *
 * @author liuruojing
 * @since 2019-03-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    UserRoleRelationMapper userRoleRelationMapper;
    @Override
    public boolean updatePasswordByUserId(long id, String password) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("password", password);
        wrapper.set("updated_time", LocalDateTime.now());
        return update(wrapper.eq("id", id));
    }

    @Override
    public IPage<User> showUsers(int pageSize, int pageNum, String searchName) {
        if (searchName != null) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            return page(new Page<User>(pageNum, pageSize),
                    wrapper.eq("username", searchName));
        } else {
            return page(new Page<User>(pageNum, pageSize));
        }
    }

    @Override
    public boolean updateStatu(String id, short statu) {
        UpdateWrapper<User> wrapper = new UpdateWrapper();
        wrapper.set("status", statu);
        wrapper.set("updated_time", LocalDateTime.now());
        return update(wrapper.eq("id", id));
    }

    @Override
    @Transactional
    public boolean deleteUsers(String userIds) {
        // 将id转成整型数组
        String[] ids = userIds.split(",");
        Long[] _ids = new Long[ids.length];
        int i = 0;
        for (String id : ids) {
            _ids[i++] = Long.parseLong(id);
        }
        // 批量删除用户
        boolean bool = removeByIds(Arrays.asList(_ids));
        //删除用户与角色之间的关联关系
        userRoleRelationMapper.deleteByUserIds(_ids);
        return bool;
    }
}
