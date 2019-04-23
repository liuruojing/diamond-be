package com.jarvan.auth.dto.permissionType;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <b><code>PermissionTypeDto</code></b>
 * <p>
 * 权限类型列表展示dto.
 * <p>
 * <b>Creation Time:</b> 2019/4/12 11:59.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Data
public class PermissionTypeDto {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String perTypeName;

    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime createdTime;


    private String createdUserName;

    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime updatedTime;


    private String updatedUserName;
}
