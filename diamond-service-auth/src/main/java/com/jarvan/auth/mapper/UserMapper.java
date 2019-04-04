package com.jarvan.auth.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jarvan.auth.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectAll(Page objectPage, @Param("searchName") String searchName,@Param("roleId") Long roleId);

    IPage<User> selectAlls(Page<User> userPage, @Param("searchName") String searchName);
}
