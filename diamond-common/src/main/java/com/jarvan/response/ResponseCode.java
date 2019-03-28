package com.jarvan.response;

/**
 * Created by Administrator on 2017/7/30.
 * @author liuruojing
 */
public enum ResponseCode {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR");

    private final int code;
    private final String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
