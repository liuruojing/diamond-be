package com.jarvan.controller;

import com.jarvan.response.ServerResponse;
import com.jarvan.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b><code>PermissionController</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2019/3/28 16:45.
 *
 * @author liuruojing
 * @since diamond-be 0.1.0
 */
@RestController
@Slf4j
@Api(tags="权限管理")
@RequestMapping("/v1.0")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    /**
     * return the premission type list.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @GetMapping(value = "/permission/types", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "权限分类列表", notes = "显示所有权限分类")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 500, message = "internal server error") })
    public final ServerResponse<?> getPermissionTypes(){
        return ServerResponse.success(permissionService.selectAll());
    }
}
