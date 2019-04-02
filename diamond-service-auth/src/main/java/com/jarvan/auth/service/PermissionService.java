package com.jarvan.auth.service;

import com.jarvan.auth.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-02
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 加载权限配置文件到数据库.
     *
     * @param  file 文件
     * @return true or false
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    boolean load(MultipartFile file);
}
