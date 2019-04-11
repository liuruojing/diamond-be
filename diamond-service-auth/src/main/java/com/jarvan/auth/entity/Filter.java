package com.jarvan.auth.entity;

import cn.jarvan.annotation.CellName;
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
    @CellName("权限符")
    private String perAnt;

    /**
     * 操作名，如新增用户
     */
    @CellName("操作名")
    private String operationName;
    @CellName("url")
    private String url;
    @CellName("请求方式")
    private String method;
    @JsonIgnore
    private Long createdUserId;
    @JsonIgnore
    private LocalDateTime createdTime;
    @JsonIgnore
    private Long updatedUserId;
    @JsonIgnore
    private LocalDateTime updatedTime;

    /**
     * 是否是系统自带配置
     */
    @JsonIgnore
    private Integer isPrimary;


}
