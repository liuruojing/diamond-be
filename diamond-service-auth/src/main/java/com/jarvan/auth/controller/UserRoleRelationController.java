package com.jarvan.auth.controller;

import com.jarvan.auth.service.UserRoleRelationService;
import com.jarvan.response.ServerResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户角色关联管理controller
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@RestController
@RequestMapping("/manage/user-role-relation")
@Api(tags = "后台--用户角色授权")
@Slf4j
public class UserRoleRelationController {
    @Autowired
    private UserRoleRelationService userRoleRelationService;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询某用户拥有的角色", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> roles(
            @ApiParam(value = "userId", required = true) @PathVariable(value = "userId") Long userId) {
        return ServerResponse
                .success(userRoleRelationService.selectRoleByUserId(userId));
    }

    @PutMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "为某用户授予角色", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> authorization(
            @ApiParam(value = "userId", required = true) @PathVariable(value = "userId") Long userId,
            @ApiParam(value = "roleIds", required = false) @RequestParam(value = "roleIds",required = false) String roleIds) {
        userRoleRelationService.authorization(userId, roleIds);
        return ServerResponse.success();
    }

}
