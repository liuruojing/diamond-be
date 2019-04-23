package com.jarvan.auth.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jarvan.auth.dto.permissionType.PermissionTypeDto;
import com.jarvan.auth.entity.PermissionType;
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
public interface PermissionTypeMapper extends BaseMapper<PermissionType> {

    IPage<PermissionTypeDto> selectAll(Page<PermissionType> permissionTypePage,@Param("searchName") String searchName);
}
