package com.jarvan.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jarvan.convert.DateConverter;
import com.jarvan.convert.LocalDateTimeCoverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
