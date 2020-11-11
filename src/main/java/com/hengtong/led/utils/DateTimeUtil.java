package com.hengtong.led.utils;

import com.aliyun.openservices.shade.org.apache.commons.lang3.time.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class DateTimeUtil {

    /**
     * Date 转 yyyy-MM 格式
     */
    public String getYYYYMMDD(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }


    /**
     * 计算某月的开始时间
     */
    public Date getMonthBeginDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                1, 0, 0, 0);
        return calendar.getTime();
    }


    /**
     * 计算某个月的结束时间
     */
    public Date getMonthEndDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(5);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                day, 23, 59, 59);
        return calendar.getTime();
    }


    /**
     * 获取某天的开始时间
     */
    public static Date getDayBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar.getTime();
    }


    /**
     * 获取某天的结束时间
     */
    public static Date getDayEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime();
    }


    /**
     * 计算某日期的上n个月 yyyy-MM  字符串格式
     */
    public static String getPrevMonth(Date date, int n) {

        Date lastMonth = DateUtils.addMonths(date, -n);
        return new SimpleDateFormat("yyyy-MM").format(lastMonth);
    }


    /**
     * 获取前n天的日期
     */
    public static Date getLastNDay(Date date, int n) {
        return DateUtils.addDays(date, -n);
    }


    /**
     * 获取两个日期之间的所有日期
     */
    public List<String> getAllDateBetween(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 声明保存日期集合
        List<String> list = new ArrayList<>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                list.add(getYYYYMMDD(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            log.info("", e);
        }
        return list;
    }
}
