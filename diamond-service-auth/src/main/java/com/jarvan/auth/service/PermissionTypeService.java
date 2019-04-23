package com.jarvan.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jarvan.auth.dto.permissionType.PermissionTypeDto;
import com.jarvan.auth.entity.PermissionType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
public interface PermissionTypeService extends IService<PermissionType> {

    PermissionType save(String name);

    void delete(String ids);

    IPage<PermissionTypeDto> selectAll(Integer pageNum, Integer pageSize, String searchName);
}
