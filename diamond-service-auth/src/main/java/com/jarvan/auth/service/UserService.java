package com.jarvan.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jarvan.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuruojing
 * @since 2019-03-29
 */
public interface UserService extends IService<User> {

    boolean updatePasswordByUserId(long id,String password);

    /**
     * 显示用户列表.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    IPage<User> showUsers(int pageSize, int pageNum, String searchName);

    /**
     * 根据id更新改用户的账户状态为可用或者不可用
     *
     * @param id userId
     * @param statu 账号状态 0不可用，1可用
     * @return ture or false
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    boolean updateStatu(String id, short statu);
}
