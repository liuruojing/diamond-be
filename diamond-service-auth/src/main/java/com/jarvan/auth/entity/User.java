package com.jarvan.auth.entity;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * <p>
 * </p>
 *
 * @author liuruojing
 * @since 2019-03-29
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;

    private String sex;

    private Integer age;

    /**
     * 账户状态
     */
    private Integer status;

    private String mobile;

    private String email;

    @JsonIgnore
    private LocalDateTime createdTime;

    @JsonIgnore
    private LocalDateTime updatedTime;

    private String wechat;

    private String qq;

    public User(){
        super();
    }
    public User(Object otherUser) {
        Class<?> clazz = otherUser.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                try {
                    Field ufield = this.getClass()
                            .getDeclaredField(field.getName());
                    ufield.set(this, field.get(otherUser));
                } catch (NoSuchFieldException e) {
                    // do nothing
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("exception", e);
            }

        }
    }

}
