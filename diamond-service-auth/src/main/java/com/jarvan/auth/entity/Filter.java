package com.jarvan.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("auth_filter")
public class Filter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 如perms[user:add,user:delete]
     */
    private String perAnt;

    /**
     * 操作名，如新增用户
     */
    private String operationName;

    private String url;

    private String method;

    private Long createdUserId;

    private LocalDateTime createdTime;

    private Long updatedUserId;

    private LocalDateTime updatedTime;

    /**
     * 是否是系统自带配置
     */
    private Integer isPrimary;


}
