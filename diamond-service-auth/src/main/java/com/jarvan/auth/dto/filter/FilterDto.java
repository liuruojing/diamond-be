package com.jarvan.auth.dto.filter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <b><code>FilterDto</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/4/11 15:56.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@Data
public class FilterDto {
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
    /**
    *
    *请求方式.
    *
    *@since ${PROJECT_NAME} 0.1.0
    */
    private String method;

    private String createdUserName;
    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime createdTime;

    private String updatedUserName;
    @JsonFormat(pattern = "yyyy-MM-hh HH:mm:ss")
    private LocalDateTime updatedTime;


}
