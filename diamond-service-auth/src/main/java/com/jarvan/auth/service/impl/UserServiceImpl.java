package com.jarvan.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jarvan.auth.dto.user.UserDto;
import com.jarvan.auth.entity.User;
import com.jarvan.auth.mapper.UserMapper;
import com.jarvan.auth.service.UserRoleRelationService;
import com.jarvan.auth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jarvan.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户管理serviceImpl
 * </p>
 *
 * @author liuruojing
 * @since 2019-03-29
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean updatePasswordByUserId(long id, String password) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("password", password);
        wrapper.set("updated_time", LocalDateTime.now());
        return update(wrapper.eq("id", id));
    }

    @Override
    public IPage<?> showUsers(int pageSize, int pageNum, String searchName,
            Long roleId) {
        if (roleId != null) {
            // where查询，仅查出赋予指定角色的用户
            return userMapper.selectAll(
                    new Page<UserDto>(pageNum, pageSize), searchName,
                    roleId);
        } else {
            // 左查询，可以查出所有用户，即使未授予角色的用户
            return userMapper.selectAlls(
                    new Page<UserDto>(pageNum, pageSize), searchName);
        }
    }

    @Override
    public boolean updateStatu(Long id, short statu) {
        UpdateWrapper<User> wrapper = new UpdateWrapper();
        wrapper.set("status", statu);
        wrapper.set("updated_time", LocalDateTime.now());
        return update(wrapper.eq("id", id));
    }

    @Override
    @Transactional
    public void deleteUsers(String userIds) {
        List<Long> _ids = ConvertUtil.convert(userIds, ",");
        if (_ids.size() == 1) {
            if (!checkUser(_ids.get(0))) {
                throw new IllegalArgumentException("用户不存在");
            }
        }
        log.debug("删除用户-角色授权关系");
        userRoleRelationService.deleteByUserIds(_ids);
        log.debug("更新用户 " + userIds+" 状态为注销");
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("status",-1);
        wrapper.in("id",_ids);
        update(wrapper);

    }

    @Override
    public boolean checkUser(Long id) {
        return getById(id) != null ? true : false;
    }
}