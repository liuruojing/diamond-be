package com.jarvan.util;

import java.util.Arrays;
import java.util.List;

/**
 * <b><code>ConvertUtil</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/4/10 15:58.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
public class ConvertUtil {
    public static List<Long> convert(String str, String separator){
        String[] strs = str.split(separator);
        Long[] ids = new Long[strs.length];
        int i = 0;
        for (String _str : strs) {
            ids[i++] = Long.parseLong(_str);
        }
        return Arrays.asList(ids);
    }
}
