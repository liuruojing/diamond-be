package com.jarvan.auth.service.impl;

import com.jarvan.auth.entity.User;
import com.jarvan.auth.mapper.UserMapper;
import com.jarvan.auth.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
