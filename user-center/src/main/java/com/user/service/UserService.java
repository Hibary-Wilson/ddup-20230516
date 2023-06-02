package com.user.service;

import com.user.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 查询用户信息
     * @param user
     * @return
     */
    List<User> findUser(User user);

}
