package com.jarvan.service.impl;

import com.jarvan.mapper.PermissionMapper;
import com.jarvan.model.PermissionType;
import com.jarvan.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <b><code>PermissionImpl</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/3/28 16:50.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Service
@Slf4j
public class PermissionImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public List<PermissionType> selectAll() {
        return permissionMapper.selectAll();
    }
}
