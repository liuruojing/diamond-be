package com.jarvan.auth.dto.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <b><code>AddUserDto</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/3/30 15:39.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Data
public class AddUserDto {
    private String username;

    private String password;
    /**
    *
    *账户状态.
    *
    *@since ${PROJECT_NAME} 0.1.0
    */
    private Integer status;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createdTime;

}
