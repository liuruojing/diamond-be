package com.jarvan.auth.dto.permission;

import cn.jarvan.annotation.CellName;
import lombok.Data;

import java.io.Serializable;


/**
 * <b><code>ExcelPermissionDto</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/4/2 18:16.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Data
public class ExcelPermissionDto implements Serializable {

    @CellName("权限名称")
    private String perName;

    /**
     * 权限类型
     */
    @CellName("权限类型")
    private String perType;

    /**
     * 权限符,形如user:add
     */
    @CellName("权限符")
    private String perAnt;
}
