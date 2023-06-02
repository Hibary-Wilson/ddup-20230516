package com.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @program: ddup-20230516
 * @description: 用户实体类
 * @author: Mr.Huang
 * @create: 2023-06-02 11:33
 **/
@Data
@TableName("t_user")
public class User {
    private  static final long serialVersionUID = 1L;
    // 主键
    private String id;
    // 姓名
    private String uname;
    // 性别
    private String sex;
    // 年龄
    private String age;
    // 出生日期
    private String birthday;
    // 名族
    private String nation;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 居住地址
    private String address;
    // 婚姻状况
    private String marriage;
    // 教育程度
    private String education;
    // 证件号码
    private String identity;
    // 国籍
    private String nationality;
    // 删除标记
    private String flag;
    // 创建人
    private String createBy;
    // 创建日期
    private Date createTime;
    // 修改人
    private String updateBy;
    // 更新日期
    private Date updateTime;

}
