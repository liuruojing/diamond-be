package com.jarvan.response;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * 一个通用的，可高复用的响应数据类
 * @param <T>
 *     使用泛型是为了适应多种数据类型
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//jackson注解,null值不返回
public class ServerResponse<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private int status;
    private String message = "success";
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String message) {
        this(status);
        this.message = message;
    }

    private ServerResponse(int status, T data) {
        this(status);
        this.data = data;
    }

    private ServerResponse(int status, String message, T data) {
        this(status, message);
        this.data = data;
    }
    @JsonIgnore
    public boolean isSuccess() {
        return status == ResponseCode.SUCCESS.getCode();
    }
    /*
        为返回正确的响应构造四种不同的方法，提供不同的正确的响应
     */
    public static <T> ServerResponse<T> success() {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());
    }

    public static <T> ServerResponse<T> success(String message) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> ServerResponse<T> success(T data) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc(),data);
    }

    public static <T> ServerResponse<T> success(String message, T data) {
        return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    /*
       提供三种 返回错误 消息的 方法，  因为错误时不需要返回 data， 所以少两个方法
     */
    public static  <T> ServerResponse<T> error() {
        return  new ServerResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static  <T> ServerResponse<T> error(String message) {
        return  new ServerResponse<>(ResponseCode.ERROR.getCode(), message);
    }

    public static <T> ServerResponse<T> error(T data) {
        return new ServerResponse<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc(), data);
    }

    public static <T> ServerResponse<T> error(String message, T data) {
        return new ServerResponse<>(ResponseCode.ERROR.getCode(), message, data);
    }
    /*
        供其他情况使用， 比如  need_login， ILLEGAL_LOGIN  情况使用
     */
    public static  <T> ServerResponse<T> error(ResponseCode responseCode, String message) {
        return  new ServerResponse<>(responseCode.getCode(), message);
    }



}
