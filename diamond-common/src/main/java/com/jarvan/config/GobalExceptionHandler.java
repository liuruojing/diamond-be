/*
 * 广州丰石科技有限公司拥有本软件版权2018并保留所有权利。
 * Copyright 2018, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved
 */

package com.jarvan.config;

import com.jarvan.response.ServerResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

/**
 * <b><code>GobalExceptionHandler</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2018/10/29 14:20.
 *
 * @author huweihui
 */
@RestControllerAdvice
@Slf4j
public class GobalExceptionHandler {
    /**
     * 全局异常捕捉处理
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(Exception e) {
        log.error("[GobalExceptionHandler]:", e);
        return ServerResponse.error();
    }

}
