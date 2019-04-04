package com.jarvan.auth.service.impl;

import com.jarvan.auth.entity.Filter;
import com.jarvan.auth.mapper.FilterMapper;
import com.jarvan.auth.service.FilterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@Service
public class FilterServiceImpl extends ServiceImpl<FilterMapper, Filter> implements FilterService {

}
