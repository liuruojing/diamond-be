package com.jarvan.auth.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jarvan.auth.dto.permissionType.PermissionTypeDto;
import com.jarvan.auth.entity.Permission;
import com.jarvan.auth.entity.PermissionType;
import com.jarvan.auth.mapper.PermissionTypeMapper;
import com.jarvan.auth.service.PermissionService;
import com.jarvan.auth.service.PermissionTypeService;
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
 * 服务实现类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@Service
@Slf4j
public class PermissionTypeServiceImpl
        extends ServiceImpl<PermissionTypeMapper, PermissionType>
        implements PermissionTypeService {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionTypeMapper permissionTypeMapper;

    @Override
    public PermissionType save(String name) {
        PermissionType record = new PermissionType();
        record.setCreatedTime(LocalDateTime.now());
        record.setIsPrimary(0);
        record.setPerTypeName(name);
        // todo 设置创建人
        save(record);
        return record;
    }

    @Override
    @Transactional
    public void delete(String ids, boolean bool) {
        List<Long> _ids = ConvertUtil.convert(ids, ",");
        if (bool) {
            log.debug("将从属于 " + ids + " 权限类型的权限删除");
            permissionService.deleteByPerTypeId(_ids);
        } else {
            log.debug("将从属于" + ids + "权限类型的权限置为游离权限");
            permissionService.updatePerTypeToUncorrelated(_ids);
        }
        log.debug("删除" + ids + "权限类型");
        removeByIds(_ids);

    }

    @Override
    public IPage<PermissionTypeDto> selectAll(Integer pageNum, Integer pageSize,
            String searchName) {
        return permissionTypeMapper.selectAll(
                new Page<PermissionType>(pageNum, pageSize), searchName);

    }

    @Override
    public List<Permission> selectPermissionListByPermissionTypeId(Long id) {
        return permissionService.selectByTypeId(id);
    }

    @Override
    public boolean checkPermissionType(Long id) {
        return getById(id) != null ? true : false;
    }
}
