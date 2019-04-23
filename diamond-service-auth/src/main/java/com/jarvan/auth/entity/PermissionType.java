package com.jarvan.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@Data
@TableName("auth_permission_type")
public class PermissionType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String perTypeName;

    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime createdTime;

    @JsonIgnore
    private Long createdUserId;

    @JsonIgnore
    private LocalDateTime updatedTime;

    @JsonIgnore
    private Long updatedUserId;

    /**
     * 是否为系统自带的默认权限
     */
    @JsonIgnore
    private Integer isPrimary;

}
