package com.jarvan.convert;

import org.springframework.core.convert.converter.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * <b><code>LocalDateTimeCoverter</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/4/1 15:29.
 *
 * @author liuruojing
 * @since nile-cmgdnm-scenicspot-be 0.1.0
 */
public class LocalDateTimeCoverter implements Converter<String,LocalDateTime> {
    @Override
    public LocalDateTime convert(String s) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s,df);
    }
}
