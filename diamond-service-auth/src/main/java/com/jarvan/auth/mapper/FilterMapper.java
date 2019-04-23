package com.jarvan.auth.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jarvan.auth.dto.filter.FilterDto;
import com.jarvan.auth.entity.Filter;
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
public interface FilterMapper extends BaseMapper<Filter> {

    IPage<FilterDto> selectAll(Page<FilterDto> filterDtoPage, @Param("searchName") String searchName);

    FilterDto showDetail(Long id);
}
