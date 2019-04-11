package com.jarvan.auth.controller;


import com.jarvan.response.ServerResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  权限类型控制器
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@RestController
@RequestMapping("/auth/permission-type")
@Slf4j
@Api(tags="权限管理")
public class PermissionTypeController {
//  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//  @ApiOperation(value = "新增一个权限类型", notes = " ")
//  @ApiResponses(value = {
//              @ApiResponse(code = 200, message = "successful request"),
//              @ApiResponse(code = 400, message = "bad request"),
//              @ApiResponse(code = 404, message = "not found"),
//              @ApiResponse(code = 500, message = "internal server error") })
//      public ServerResponse<?> method(@ApiParam(value = " ", required = true) @PathVariable(value = " ") String param1,
//                                      @ApiParam(value = " ", required = true) @RequestParam(value = " ") String param2){
//          return ServerResponse.success();
//      }

}
