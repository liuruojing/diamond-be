package com.jarvan.auth.mapper;

import com.jarvan.auth.entity.UserRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuruojing
 * @since 2019-03-29
 */
@Repository
public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation> {

    boolean deleteByUserIds(Long[] ids);
}
