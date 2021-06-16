package com.item.collect.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zcm
 */
public class DateUtil {

    public static LocalDateTime getNow(){
        return LocalDateTime.now();
    }
    public static String getNowTime(){
        return LocalDateTime.now().toString();
    }

    public static void main(String[] args) {
        System.out.println(getNowTime());
        LocalDateTime localDateTime = LocalDateTime.parse(getNowTime());
        System.out.println(localDateTime.toString());
    }

}
