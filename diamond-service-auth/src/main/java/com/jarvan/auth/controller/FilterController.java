package com.jarvan.auth.controller;


import com.jarvan.response.ServerResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@RestController
@RequestMapping("/auth/filter")
@Slf4j
@Api(tags = "url拦截规则配置")
public class FilterController {
 @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
 @ApiOperation(value = "查看具体详细信息", notes = " ")
 @ApiResponses(value = {
             @ApiResponse(code = 200, message = "successful request"),
             @ApiResponse(code = 400, message = "bad request"),
             @ApiResponse(code = 404, message = "not found"),
             @ApiResponse(code = 500, message = "internal server error") })
     public ServerResponse<?> show(@ApiParam(value = "id", required = true) @PathVariable(value = "id") Long id){

         return ServerResponse.success();
     }

}
