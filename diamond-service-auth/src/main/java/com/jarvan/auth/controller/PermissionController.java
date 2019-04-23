package com.jarvan.auth.controller;

import com.jarvan.auth.dto.permission.AddPermissionDto;
import com.jarvan.auth.service.PermissionService;
import com.jarvan.response.ServerResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "后台--权限管理")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @PostMapping(value = "/permission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "新增权限", notes = "新增一个权限，指定权限类型，如果为游离权限将权限类型id置为0")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> save(
            @ApiParam(value = "permission", required = true) @RequestBody() AddPermissionDto permission) {
        check(permission);
        return ServerResponse.success(permissionService.save(permission));
    }

    private void check(AddPermissionDto permission) {
        if(permission.getPerName().length()>8||permission.getPerName().length()<3||permission.getPerName().contains(" "))
        {
            throw new IllegalArgumentException("权限名称必须在[3,8]字节范围内且不能有空格");
        }
        if(permission.getPerAnt().length()>8||permission.getPerAnt().length()<3||permission.getPerAnt().contains(" "))
        {
            throw new IllegalArgumentException("权限配置符必须在[3,8]字节范围内且不能有空格");
        }
    }

    @DeleteMapping(value = "/permission", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除指定权限", notes = "支持单个或批量删除，批量删除时用','隔开")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> delete(
            @ApiParam(value = "ids", required = true) @RequestParam(value = "ids") String ids) {
        permissionService.delete(ids);
        return ServerResponse.success();
    }
    @PutMapping(value = "/permission/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改权限所从属的权限类型", notes = "如果是想将权限置于游离权限，将perTypeId置为-1")
    @ApiResponses(value = {
                @ApiResponse(code = 200, message = "successful request"),
                @ApiResponse(code = 400, message = "bad request"),
                @ApiResponse(code = 404, message = "not found"),
                @ApiResponse(code = 500, message = "internal server error") })
        public ServerResponse<?> update(@ApiParam(value = "id", required = true) @PathVariable(value = "id") Long id,
                                        @ApiParam(value = "perTypeId", required = true) @RequestParam(value = "perTypeId") Long perTypeId){
          permissionService.updatePerTypeId(id,perTypeId);
          return ServerResponse.success();
        }

}
