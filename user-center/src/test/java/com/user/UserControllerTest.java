package com.user;

import com.alibaba.fastjson.JSONObject;
import com.user.controller.UserController;
import com.user.entity.User;
import com.user.util.ResultUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserControllerTest extends BaseTest{

    @Autowired
    UserController controller;

    @Test
    void testFindUser() {
        User user = new User();
        user.setId("1");
        user.setPhone("15145214208");
        ResultUtil resultUtil = controller.findUser(user);
        System.out.println("用户信息：" + JSONObject.toJSONString(resultUtil.getData()));
    }

}
