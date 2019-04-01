package com.jarvan.auth.service.impl;

import com.jarvan.auth.entity.Role;
import com.jarvan.auth.mapper.RoleMapper;
import com.jarvan.auth.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuruojing
 * @since 2019-03-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
