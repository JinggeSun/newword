package com.item.streaming;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.parse("2020-01-01 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String va =  localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println(va);
    }
}
