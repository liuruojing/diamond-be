package com.jarvan.service;

import com.jarvan.model.PermissionType;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b><code>PermissionService</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/3/28 16:49.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Resource
public interface PermissionService {

    List<PermissionType> selectAll();
}
