package com.jarvan.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jarvan.auth.dto.permissionType.PermissionTypeDto;
import com.jarvan.auth.entity.Permission;
import com.jarvan.auth.entity.PermissionType;
import com.jarvan.auth.service.PermissionTypeService;
import com.jarvan.response.ResponseCode;
import com.jarvan.response.ServerResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限类型控制器
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-04
 */
@RestController
@RequestMapping("/manage")
@Slf4j
@Api(tags = "后台--权限管理")
public class PermissionTypeController {
    @Autowired
    private PermissionTypeService permissionTypeService;

    @PostMapping(value = "/permission-type", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "新增一个权限类型", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> save(
            @ApiParam(value = "name", required = true) @RequestParam(value = "name") String name) {
        check(name);
        try {
            return ServerResponse.success(permissionTypeService.save(name));

        } catch (DuplicateKeyException e) {
            return ServerResponse.error(ResponseCode.BAD_REQUEST, "权限类型名称已存在");
        }
    }

    @DeleteMapping(value = "/permission-type", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id删除权限类型", notes = "支持批量删除,多id用','分隔")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> delete(
            @ApiParam(value = "ids", required = true) @RequestParam(value = "ids") String ids,
            @ApiParam(value = "deletePermission", required = true) @RequestParam(value = "deletePermission") boolean bool) {
        permissionTypeService.delete(ids,bool);
        log.info("删除权限类型" + ids + "成功!");
        return ServerResponse.success();

    }

    @GetMapping(value = "/permission-types", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询权限类型列表", notes = "可根据权限名称模糊查询")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> selectAll(
            @ApiParam(value = "pageNum", required = true) @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "pageSize", required = true) @RequestParam(value = "pageSize") Integer pageSize,
            @ApiParam(value = "searchName") @RequestParam(value = "searchName", required = false) String searchName) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageNum = 10;
        }
        IPage<PermissionTypeDto> page = permissionTypeService.selectAll(pageNum,
                pageSize, searchName);
        return ServerResponse.success(page);
    }

    @GetMapping(value = "/permission-type/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "显示该权限类型下的所有权限信息列表", notes = "如果不传显示游离权限")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> selectByTypeId(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") Long id) {

        return ServerResponse.success(permissionTypeService
                .selectPermissionListByPermissionTypeId(id));
    }

    private void check(String name) {
        if ("".equals(name.trim()) || name.contains(" ")) {
            throw new IllegalArgumentException("权限类型名称不能包含空白字符");
        }
        if (name.length() < 3 || name.length() > 8) {
            throw new IllegalArgumentException("权限类型名称必须在[3,8]个长度之间");
        }
    }
}
