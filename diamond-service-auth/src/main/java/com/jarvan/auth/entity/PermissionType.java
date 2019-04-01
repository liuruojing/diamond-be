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
 * @since 2019-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PermissionType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String perTypeName;

    private LocalDateTime createdTime;

    private Long createdUserId;

    private LocalDateTime updatedTime;

    private Long updatedUserId;


}
