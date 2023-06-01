package com.user.controller;

import com.user.util.ResultUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: ddup-20230516
 * @description: 登录管理器
 * @author: Mr.Huang
 * @create: 2023-05-18 00:02
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    /**
     * 登录
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "{adminPath}/login", method = RequestMethod.GET)
    public ResultUtil userLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultUtil result = ResultUtil.success();

        return result;
    }
}
