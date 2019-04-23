package com.jarvan.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jarvan.auth.dto.filter.FilterDto;
import com.jarvan.auth.entity.Filter;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
public interface FilterService extends IService<Filter> {

    IPage<FilterDto> showAll(String searchName, Long pageNum, Long pageSize);

    void delete(String ids);

    FilterDto showDetail(Long id);

    void upload(MultipartFile file) throws IOException, IllegalAccessException, NoSuchFieldException, InstantiationException;
}
