package com.jarvan.encrypt;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * <b><code>MD5</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/4/4 15:02.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
public class MD5 {
    private static final String key ="auth";
    public static String encript(String text){
       return DigestUtils.md5Hex(text+key);
    }
}
