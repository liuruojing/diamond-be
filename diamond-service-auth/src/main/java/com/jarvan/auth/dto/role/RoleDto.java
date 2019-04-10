package com.jarvan.auth.dto.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <b><code>RoleDto</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/4/4 17:42.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Data
public class RoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String roleName;

    private String createdUserName;

    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime createdTime;

    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime updatedTime;
}
