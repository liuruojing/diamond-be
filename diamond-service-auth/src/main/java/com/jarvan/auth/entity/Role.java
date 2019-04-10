package com.jarvan.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("auth_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String roleName;
    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime createdTime;
    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime updatedTime;
    @JsonIgnore
    private Long cretedUserId;
    @JsonIgnore
    private Long updatedUserId;


}
