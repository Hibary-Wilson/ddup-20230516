package com.user.mapper;

import com.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

//@Mapper // 启动类上一添加mapper扫描，此处无需在家加注解！
public interface UserMapper {

    User findUser(User user);
}
