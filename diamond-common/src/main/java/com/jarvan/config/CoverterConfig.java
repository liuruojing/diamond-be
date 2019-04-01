package com.jarvan.config;

import com.jarvan.convert.DateConverter;
import com.jarvan.convert.LocalDateTimeCoverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <b><code>CoverterConfig</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/3/11 11:30.
 *
 * @author liuruojing
 * @since nile-cmgdnm-scenicspot-be 0.1.0
 */
@Configuration
public class CoverterConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new DateConverter());
        registry.addConverter(new LocalDateTimeCoverter());
    }

}
