package com.jarvan.auth.controller;

import com.jarvan.auth.dto.filter.FilterDto;
import com.jarvan.auth.entity.Filter;
import com.jarvan.auth.service.FilterService;
import com.jarvan.response.ResponseCode;
import com.jarvan.response.ServerResponse;
import com.jarvan.util.FileUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
@Slf4j
@Api(tags = "后台--资源管理")
public class FilterController {
    @Autowired
    private FilterService filterService;

    @GetMapping(value = "/filters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查看已配置的资源信息列表", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> showAll(
            @ApiParam(value = "searchName") @RequestParam(value = "searchName",required = false) String searchName,
            @ApiParam(value = "pageNum", required = true) @RequestParam(value = "pageNum") Long pageNum,
            @ApiParam(value = "pageSize", required = true) @RequestParam(value = "pageSize") Long pageSize) {

        return ServerResponse.success(filterService.showAll(searchName,pageNum, pageSize));
    }

    @DeleteMapping(value = "/filter/{ids}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除指定的资源配置（支持批量删除）", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> delete(
            @ApiParam(value = "ids", required = true) @PathVariable(value = "ids") String ids) {
        filterService.delete(ids);
        return ServerResponse.success();
    }

    @PutMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改资源配置", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> update(
            @ApiParam(value = "filter", required = true) @RequestBody Filter filter) {
        // todo 设置修改人id
        filter.setUpdatedTime(LocalDateTime.now());
        filter.setIsPrimary(0);
        if (filterService.updateById(filter)) {
            return ServerResponse.success();
        } else {
            return ServerResponse.error(ResponseCode.NOT_FOUND, "资源配置不存在");
        }
    }

    @GetMapping(value = "/filter/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "显示某个资源配置的具体信息", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> detail(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") Long id) {
        FilterDto data = filterService.showDetail(id);
        if (data != null) {
            return ServerResponse.success(data);
        } else {
            return ServerResponse.error(ResponseCode.NOT_FOUND, "资源配置不存在");
        }

    }

    @GetMapping(value = "/filter/templet/download", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "下载资源配置模板", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public void download(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream(
                "template" + File.separator + "资源配置表.xlsx");
        FileUtil.download(in, request, response, "资源配置表.xlsx");
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "上传资源配置文件", notes = "注意！！！此操作会删除之前所有配置的资源哦 ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ServerResponse<?> upload(
            @ApiParam(value = "file", required = true) @RequestParam(value = "file") MultipartFile file) {
        if (file != null) {
            // 得到后缀名
            String ext = getExt(file.getOriginalFilename());
            // 如果不是xlsx文件
            if (!(".xlsx".equals(ext.toLowerCase()))) {
               throw new IllegalArgumentException("文件类型错误,上传类型只能是xlsx");
            }
        }
        try {
            filterService.upload(file);
            return ServerResponse.success();
        } catch (IOException e) {
           return ServerResponse.error(ResponseCode.BAD_REQUEST,"配置文件格式错误");
        } catch (IllegalAccessException e) {
            return ServerResponse.error(ResponseCode.BAD_REQUEST,"配置文件格式错误");
        } catch (NoSuchFieldException e) {
            return ServerResponse.error(ResponseCode.BAD_REQUEST,"配置文件格式错误");
        } catch (InstantiationException e) {
            return ServerResponse.error(ResponseCode.BAD_REQUEST,"配置文件格式错误");
        }

    }
    private String getExt(final String fileName) {
        int pos = fileName.lastIndexOf(".");
        return pos == -1 ? "" : fileName.substring(pos, fileName.length());
    }
}
