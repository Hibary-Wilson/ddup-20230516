package com.eureka.base.hy;

import com.eureka.base.UnitTest;
import org.junit.Test;

/**
 * @program: ddup-20230516
 * @description: 基础语法练习
 * @author: Mr.Huang
 * @create: 2023-06-01 18:17
 **/
public class BasicGrammarExercises extends UnitTest {

    @Test
    public void testByte() {
        byte b = 12;
        short s1 = 15;
        int i1 = b + s1;
        System.out.println("计算结果：" + i1);
    }

}
