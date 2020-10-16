package com.sun.myquartz.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 * @author zcm
 */
public class DateUtil {

    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getNowString(){
         return df.format(getNow());
    }

    public static LocalDateTime getNow(){
        return LocalDateTime.now();
    }
}
