package com.jarvan.auth.dto.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * <b><code>AddPermissionDto</code></b>
 * <p>
 * 新增权限DTO.
 * <p>
 * <b>Creation Time:</b> 2019/4/23 12:23.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Data
public class AddPermissionDto {
    private String perName;

    /**
     * 权限操作符，如user:add
     */
    private String perAnt;

    /**
     * 所属权限类型id
     */
    private Long perTypeId;
}
