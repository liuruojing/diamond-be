package com.jarvan.auth.service.impl;

import cn.jarvan.core.generator.excel.ExcelGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jarvan.auth.dto.filter.FilterDto;
import com.jarvan.auth.entity.Filter;
import com.jarvan.auth.mapper.FilterMapper;
import com.jarvan.auth.service.FilterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jarvan.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
public class FilterServiceImpl extends ServiceImpl<FilterMapper, Filter>
        implements FilterService {
    @Autowired
    private FilterMapper filterMapper;

    @Override
    public IPage<FilterDto> showAll(Long pageNum, Long pageSize) {
        return filterMapper.selectAll(new Page<FilterDto>(pageNum, pageSize));

    }

    @Override
    public void delete(String ids) {
        List<Long> _ids = ConvertUtil.convert(ids, ",");
        removeByIds(_ids);

    }

    @Override
    public FilterDto showDetail(Long id) {
        return filterMapper.showDetail(id);
    }

    @Override
    @Transactional
    public void upload(MultipartFile file)
            throws IOException, IllegalAccessException, NoSuchFieldException,
            InstantiationException {
        List<Filter> records = ExcelGenerator.read(Filter.class, file);
        for (Filter record : records) {
            check(record);
        }
        //1删除之前的配置
        QueryWrapper<Filter> wrapper = new QueryWrapper<>();
        wrapper.notIn("is_primary",1);
        remove(wrapper);
        //2保存新配置
        saveBatch(records);
    }

    private void check(Filter record) {
        if (record.getUrl() == null || "".equals(record.getUrl().trim())
                || record.getPerAnt() == null
                || "".equals(record.getPerAnt().trim())
                || record.getOperationName() == null
                || "".equals(record.getOperationName().trim())) {
            throw new IllegalArgumentException("上传文件的配置字段不能为空,请检查你的配置条目");
        }
        if (record.getMethod() == null || "".equals(record.getMethod())) {
            throw new IllegalArgumentException("请求方式不能为空,请检查你的配置条目");
        }
        record.setIsPrimary(0);
        record.setCreatedTime(LocalDateTime.now());
        //todo 设置创建人
    }

}
