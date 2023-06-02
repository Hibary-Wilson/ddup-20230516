package com.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.user.entity.User;
import com.user.service.UserService;
import com.user.util.ResponseCode;
import com.user.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ddup-20230516
 * @description: 用户管理控制器
 * @author: Mr.Huang
 * @create: 2023-06-02 14:17
 **/
@RestController
@Slf4j
@RequestMapping("/userCenter/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查找用户信息
     * @param user
     * @return
     */
    @RequestMapping("findUser")
    public ResultUtil findUser(@RequestBody User user) {
        log.info("用户查询请求参数：{}", JSONObject.toJSONString(user));
        ResultUtil resultUtil = ResultUtil.success();
        try {
            // 参数校验
            if (null == user) {
                resultUtil.setCodeAndInfo(ResponseCode.REQUEST_PARAM_ERROR);
                return resultUtil;
            }
            resultUtil.setData(userService.findUser(user));
        } catch (Exception e) {
            log.error("获取用户信息异常，{}",e);
            resultUtil.setCodeAndInfo(ResponseCode.ERROR);
        }

        return resultUtil;
    }

}
