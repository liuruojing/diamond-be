package com.jarvan.auth.controller;

import com.jarvan.auth.entity.Role;
import com.jarvan.auth.service.RoleService;
import com.jarvan.response.ResponseCode;
import com.jarvan.response.ServerResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@RestController
@RequestMapping("/manage")
@Api(tags = "后台--角色管理")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 新增角色
     * 
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @PostMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "新增一个角色资源", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "角色名为空或者角色名已经存在"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> save(
            @ApiParam(value = "roleName", required = true) @RequestParam(value = "roleName") String roleName) {
        if (roleName.trim().length() > 0) {
            Role role = new Role();
            role.setRoleName(roleName);
            role.setCreatedTime(LocalDateTime.now());
            try {
                roleService.save(role);
                return ServerResponse.success(role);
            } catch (DuplicateKeyException e) {
                return ServerResponse.error(ResponseCode.BAD_REQUEST,
                        "角色名已经存在");
            }
        } else {
            return ServerResponse.error(ResponseCode.BAD_REQUEST,
                    "角色名不能由空白字符组成");
        }

    }

    /**
     * 删除角色
     * 
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @DeleteMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id删除角色", notes = "支持批量删除，用','号隔开 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> delete(
            @ApiParam(value = "ids", required = true) @RequestParam(value = "ids") String ids) {
        roleService.delete(ids);
        return ServerResponse.success();

    }

    /**
     * 显示角色列表
     * 
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "显示角色列表", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "参数类型错误"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> roleList(
            @ApiParam(value = "pageSize", required = true) @RequestParam(value = "pageSize") Integer pageSize,
            @ApiParam(value = "pageNum", required = true) @RequestParam(value = "pageNum") Integer pageNum) {
        if (pageSize < 1) {
            pageSize = 10;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
        return ServerResponse.success(roleService.showRoles(pageSize, pageNum));

    }

}
