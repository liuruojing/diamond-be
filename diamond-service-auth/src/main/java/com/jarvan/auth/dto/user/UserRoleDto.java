package com.jarvan.auth.dto.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <b><code>UserRoleDto</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/4/4 16:36.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Data
public class UserRoleDto {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;


    private String sex;

    private Integer age;

    /**
     * 账户状态
     */
    private Integer status;

    private String mobile;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime createdTime;

    private String wechat;

    private String qq;

    private String roleName;
}
