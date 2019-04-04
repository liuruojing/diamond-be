package com.jarvan.auth.service.impl;

import com.jarvan.auth.entity.Log;
import com.jarvan.auth.mapper.LogMapper;
import com.jarvan.auth.service.LogService;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
