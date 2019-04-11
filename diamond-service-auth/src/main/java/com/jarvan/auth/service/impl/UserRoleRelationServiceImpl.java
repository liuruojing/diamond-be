package com.jarvan.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jarvan.auth.entity.UserRoleRelation;
import com.jarvan.auth.mapper.UserRoleRelationMapper;
import com.jarvan.auth.service.RoleService;
import com.jarvan.auth.service.UserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jarvan.auth.service.UserService;
import com.jarvan.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.LinkedList;
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
public class UserRoleRelationServiceImpl
        extends ServiceImpl<UserRoleRelationMapper, UserRoleRelation>
        implements UserRoleRelationService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void deleteByUserIds(List<Long> ids) {
        QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper();
        wrapper = wrapper.in("user_id", ids);
        remove(wrapper);
    }

    @Override
    public void deleteByRoleIds(List<Long> ids) {
        QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper();
        wrapper = wrapper.in("role_id", ids);
        remove(wrapper);

    }

    @Override
    public List<UserRoleRelation> selectRoleByUserId(Long userId) {
        if(userService.checkUser(userId)) {
            QueryWrapper<UserRoleRelation> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            return list(wrapper);
        }else{
            throw new IllegalArgumentException("用户不存在");
        }
    }

    @Override
    @Transactional
    public void authorization(Long userId, String roleIds) {
        if (userService.checkUser(userId)) {
            // 1删除原有的用户授权信息
            List<Long> list = new LinkedList<>();
            list.add(userId);
            deleteByUserIds(list);
            if(roleIds!=null){
            // 2添加新的用户授权信息
            List<Long> _roleIds = ConvertUtil.convert(roleIds, ",");
            List<UserRoleRelation> relations = new LinkedList<>();
            for (Long roleId : _roleIds) {
                if (roleService.checkRole(roleId)) {
                    UserRoleRelation relation = new UserRoleRelation();
                    relation.setUserId(userId);
                    relation.setRoleId(roleId);
                    // todo 设置创建人
                    relation.setCreatedTime(LocalDateTime.now());
                    relations.add(relation);
                }
            }
            if (relations.size() > 0) {
                saveBatch(relations);
            }}
        } else {
            throw new IllegalArgumentException("用户不存在");
        }
    }
}
