package com.jarvan.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * <b><code>DateConverter</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/11/24 12:44.
 *
 * @author Hu Weihui
 */
@Slf4j
public class DateConverter implements Converter<String, Date> {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Date convert(String source) {

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            return formatter.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("string to date exception:", e);
        }

    }
}
