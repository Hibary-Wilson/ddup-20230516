package com.user;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: ddup-20230516
 * @description: 测试基类
 * @author: Mr.Huang
 * @create: 2023-06-02 15:14
 **/
@SpringBootTest  // 如果需要使用spring容器中的bean就添加该注解，否则直接使用runwith也可以
@RunWith(SpringRunner.class)
public class BaseTest {

}
