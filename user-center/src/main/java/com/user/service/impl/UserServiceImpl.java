package com.user.service.impl;

import com.user.entity.User;
import com.user.mapper.UserMapper;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ddup-20230516
 * @description: 用户管理service实现类
 * @author: Mr.Huang
 * @create: 2023-06-02 11:51
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUser(User user) {
        List<User> list = new ArrayList<>();
        list.add(userMapper.findUser(user));
        return list;
    }
}
