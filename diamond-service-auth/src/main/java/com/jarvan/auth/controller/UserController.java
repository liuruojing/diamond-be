package com.jarvan.auth.controller;

import com.jarvan.auth.dto.user.AddUserDto;
import com.jarvan.auth.entity.User;
import com.jarvan.auth.service.UserService;
import com.jarvan.encrypt.MD5;
import com.jarvan.response.ResponseCode;
import com.jarvan.response.ServerResponse;
import com.jarvan.util.ClassUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户管理controller
 * </p>
 *
 * @author liuruojing
 * @since 2019-03-29
 */
@RestController
@RequestMapping("/manage")
@Api(tags = "后台--用户管理")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param user
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @PostMapping(value = "/user")
    @ApiOperation(value = "新增用户", notes = "用户名长度必须在[4,8]范围内,密码长度在[6,15]个字符内,status为用户状态,1为可用,0为不可用")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> save(@RequestBody AddUserDto user) {
        try {
            check(user);
        } catch (IllegalArgumentException e) {
            return ServerResponse.error(ResponseCode.BAD_REQUEST,
                    e.getMessage());
        }
        // 将dto转成实体类
        User insertUser = ClassUtil.transfer(user, User.class);
        // 将密码进行MD5加密
        insertUser.setPassword(MD5.encript(insertUser.getPassword()));
        insertUser.setCreatedTime(LocalDateTime.now());
        try {
            userService.save(insertUser);
            log.info("新增用户成功" + insertUser);
            return ServerResponse.success(insertUser);
        } catch (DuplicateKeyException e) {
            log.debug("用户名已存在", e);
            return ServerResponse.error(ResponseCode.BAD_REQUEST, "用户名已存在");
        }

    }

    /**
     * 删除用户
     *
     * @param userIds ids
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除用户", notes = "根据id删除用户,支持批量删除,id用逗号分隔。形如1,2,3")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> delete(
            @ApiParam(value = "userIds", required = true) @RequestParam(value = "userIds") String userIds) {
        userService.deleteUsers(userIds);
            return ServerResponse.success();

    }

    /**
     * 修改用户密码
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @PutMapping(value = "/user/password/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> update(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") long id,
            @ApiParam(value = "password", required = true) @RequestParam(value = "password") String password) {
        if (password.length() < 6 || password.length() > 15) {
            return ServerResponse.error(ResponseCode.BAD_REQUEST,
                    "密码长度必须在[6,15]个字符长度之间");
        }
        if (userService.updatePasswordByUserId(id, password))
            return ServerResponse.success();
        else
            return ServerResponse.error(ResponseCode.NOT_FOUND, "此用户不存在");
    }

    /**
     * 修改用户账号状态
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @PutMapping(value = "/user/statu/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户账号的可用状态", notes = "0为不可用,1为可用")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> update(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") Long id,
            @ApiParam(value = "statu", required = true) @RequestParam(value = "statu") short status) {
        if (userService.updateStatu(id, status))
            return ServerResponse.success();
        else
            return ServerResponse.error(ResponseCode.NOT_FOUND, "用户不存在");
    }

    /**
     * 显示用户列表，可根据用户名和所拥有的角色进行筛选.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户列表", notes = "用户列表,支持根据searchName、所拥有角色信息筛选查询")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> userList(
            @ApiParam(value = "pageNum", required = true) @RequestParam("pageNum") Integer pageNum,
            @ApiParam(value = "pageSize", required = true) @RequestParam("pageSize") Integer pageSize,
            @ApiParam(value = "searchName") @RequestParam(value = "searchName", required = false) String searchName,
            @ApiParam(value = "roleId") @RequestParam(value = "roleId", required = false) Long roleId) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        return ServerResponse.success(
                userService.showUsers(pageSize, pageNum, searchName, roleId));
    }

    private void check(AddUserDto user) {
        if (user.getUsername().length() < 3
                || user.getUsername().length() > 8) {
            throw new IllegalArgumentException("用户名必须在[4,8]个字符长度之间");
        }
        if (user.getPassword().length() < 6
                || user.getPassword().length() > 15) {
            throw new IllegalArgumentException("密码长度必须在[6,15]个字符长度之间");
        }
    }
}