package com.jarvan.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jarvan.auth.dto.user.AddUserDto;
import com.jarvan.auth.entity.User;
import com.jarvan.auth.service.UserService;
import com.jarvan.response.ServerResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/manage/auth")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "用户列表", notes = "用户列表")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> userList() {
        return ServerResponse.success(userService.page(new Page<User>()));
    }

    /**
     * 新增用户
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> method(@RequestBody AddUserDto user) {
        User userInfo = new User(user);
        return ServerResponse.success(userService.save(userInfo));
    }


}