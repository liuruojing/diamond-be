package com.jarvan.auth.service.impl;

import cn.jarvan.core.generator.excel.ExcelGenerator;
import com.jarvan.auth.dto.permission.ExcelPermissionDto;
import com.jarvan.auth.entity.Permission;
import com.jarvan.auth.mapper.PermissionMapper;
import com.jarvan.auth.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-02
 */
@Service
public class PermissionServiceImpl extends
        ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public boolean load(MultipartFile file) {
        try {
            List<ExcelPermissionDto> permissions = ExcelGenerator
                    .read(ExcelPermissionDto.class, file);
            System.out.println(int.class);
            System.out.println(Integer.class);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return false;
    }
}
