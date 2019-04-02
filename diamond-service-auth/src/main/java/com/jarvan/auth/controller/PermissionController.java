package com.jarvan.auth.controller;


import com.jarvan.auth.entity.Permission;
import com.jarvan.auth.service.PermissionService;
import com.jarvan.response.ResponseCode;
import com.jarvan.response.ServerResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 权限信息管理controller
 * </p>
 *
 * @author liuruojing
 * @since 2019-04-02
 */
@RestController
@RequestMapping("/auth/permission")
@Api(tags="后台--权限信息管理")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
   /**
    *导入权限信息表
    *@param
    *@return
    *@author liuruojing
    *@since ${PROJECT_NAME} 0.1.0
    */
   @PostMapping(value = "/upload/excel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ApiOperation(value = "导入权限信息表", notes = "导入权限信息表")
   @ApiResponses(value = {
               @ApiResponse(code = 200, message = "successful request"),
               @ApiResponse(code = 400, message = "bad request"),
               @ApiResponse(code = 500, message = "internal server error") })
       public ServerResponse<?> method(@ApiParam(value = "file", required = true) @RequestParam("file") MultipartFile file){
          if(file.isEmpty()){
              return ServerResponse.error(ResponseCode.BAD_REQUEST,"请重新选择文件");
          }
          String suffix = subSuffix(file.getOriginalFilename());
          if(".xlsx".equals(suffix)||".xls".equals(suffix)){
             permissionService.load(file);
          }else {
              return ServerResponse.error(ResponseCode.BAD_REQUEST,"上传的文件类型只能为.xlsx或者.xls");
          }
          return ServerResponse.success();
       }

    /**
     * 获取文件后缀名.
     *
     * @param originalFilename 文件名
     * @return suffix 后缀名
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    private String subSuffix(String originalFilename) {
      return originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
    }

}
