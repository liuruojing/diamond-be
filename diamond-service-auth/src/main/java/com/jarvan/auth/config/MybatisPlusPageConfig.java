package com.jarvan.auth.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <b><code>MybatisPlusPageConfig</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/4/1 18:07.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.jarvan.*.mapper.*")
public class MybatisPlusPageConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
