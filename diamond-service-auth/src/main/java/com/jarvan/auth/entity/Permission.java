package com.jarvan.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("auth_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String perName;

    /**
     * 权限操作符，如user:add
     */
    private String perAnt;

    /**
     * 所属权限类型
     */
    @JsonIgnore
    private Long perTypeId;
    @JsonIgnore
    private LocalDateTime createdTime;
    @JsonIgnore
    private Long createdUserId;
    @JsonIgnore
    private LocalDateTime updatedTime;
    @JsonIgnore
    private Long updatedUserId;

    /**
     * 项目初始化时配置的权限，不可删除
     */
    @JsonIgnore
    private Integer isPrimary;


}
