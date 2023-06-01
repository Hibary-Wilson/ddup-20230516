package com.eureka.base.hy;


import com.eureka.base.UnitTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: itheima20210917_StudyTest
 * @description: 2023-02-01
 * @author: Mr.Huang
 * @create: 2023-02-01 16:53
 **/
public class CalendarDemo extends UnitTest {

    public Calendar getCalendar(String dateStr) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        if (StringUtils.isNotBlank(dateStr)) {
            calendar.setTime(setDate(dateStr));
        }
        return calendar;
    }

    /**
     * Date格式与String格式日期互相转换
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public Date setDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = format.parse(dateStr);
        return parse;
    }

    @Test
    public void demoOne() throws ParseException {
        Calendar calendar = getCalendar(null); // 2023-02-14 12:02:01
        // 年份
        System.out.println("现在是：" + calendar.get(Calendar.YEAR) + "年");
        // 月份
        System.out.println("现在是：" + (calendar.get(Calendar.MONTH) + 1) + "月");
        System.out.println("现在是：" + (calendar.get(Calendar.MONDAY) + 1) + "月");
        // 号数
        System.out.println("现在是：" + calendar.get(Calendar.DATE) + "号");
        System.out.println("现在是：" + calendar.get(Calendar.DAY_OF_MONTH) + "号");
        // 今年的第几天
        System.out.println("现在是：" + calendar.get(Calendar.DAY_OF_YEAR) + "天");
        // 今天是周几
        System.out.println("现在是周：" +
                ((calendar.get(Calendar.DAY_OF_WEEK) > 1) ? (calendar.get(Calendar.DAY_OF_WEEK) - 1):"日"));
        // 这个月第几周
        System.out.println("现在是第：" + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "周");
        // 现在是几点
        System.out.println("现在是:" + calendar.get(Calendar.HOUR) + "点");
        // 现在是多少分
        System.out.println("现在是：" + calendar.get(Calendar.MINUTE) + "分");
        // 现在是多少秒
        System.out.println("现在是：" + calendar.get(Calendar.SECOND) + "秒");

        // 值为1970年（即计算机刚出来那会儿的时间）
        calendar.clear();
        System.out.println("日历：" + calendar.get(Calendar.YEAR) + "年"
                + calendar.get(Calendar.MONTH) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日");


    }

}
