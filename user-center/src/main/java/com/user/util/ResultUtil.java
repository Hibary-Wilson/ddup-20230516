package com.user.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: ddup-20230516
 * @description: 请求返回实体
 * @author: Mr.Huang
 * @create: 2023-05-18 00:05
 **/
@Data
public class ResultUtil<T> implements Serializable {

    private String code;  // 响应码

    private String info;  // 响应信息

    private List<T> data; // 响应数据

    public ResultUtil(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.info = responseCode.getInfo();
    }

    public ResultUtil() {
    }

    public static ResultUtil success() {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(ResponseCode.SUCCESS.getCode());
        resultUtil.setInfo(ResponseCode.SUCCESS.getInfo());

        return resultUtil;
    }

    public static ResultUtil error() {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(ResponseCode.ERROR.getCode());
        resultUtil.setInfo(ResponseCode.ERROR.getInfo());

        return resultUtil;
    }

}
