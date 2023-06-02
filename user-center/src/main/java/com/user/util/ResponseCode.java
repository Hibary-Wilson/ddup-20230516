package com.user.util;

/**
 * @program: ddup-20230516
 * @description: 响应枚举
 * @author: Mr.Huang
 * @create: 2023-05-18 00:07
 **/
public enum ResponseCode {
    SUCCESS("0","成功"),
    ERROR("1","系统错误"),
    REQUEST_PARAM_ERROR("1","请求参数错误！"),

    ;

    private String code;
    private String info;

    ResponseCode(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
