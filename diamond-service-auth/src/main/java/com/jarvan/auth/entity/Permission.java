package com.jarvan.auth.entity;


import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String perName;

    /**
     * 所属权限类型
     */
    private Long perTypeId;

    private LocalDateTime createdTime;

    private Long createdUserId;

    private LocalDateTime updatedTime;

    private Long updatedUserId;

    /**
     * 权限符,形如user:add
     */
    private String perAnt;


}
