package com.jarvan.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * <b><code>ClassUtil</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/4/4 14:19.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Slf4j
public class ClassUtil {
    /**
     * dto与entity互转的工具方法。
     *
     * @param object dto or entity instance
     * @param clazz dto.class or enrity.class
     * @return object
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static <K, T> T transfer(K object, Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }

        Class _clazz = object.getClass();
        Field[] _fields = _clazz.getDeclaredFields();
        Field field;
        for (Field _field : _fields) {
            try {
                _field.setAccessible(true);
                field = clazz.getDeclaredField(_field.getName());
                field.setAccessible(true);
                field.set(instance, _field.get(object));
            } catch (NoSuchFieldException e) {
                // do nothing
                log.debug("exception", e);
            } catch (IllegalAccessException e) {
                // do nothing
                log.debug("exception", e);
            }

        }
        return instance;
    }
}
