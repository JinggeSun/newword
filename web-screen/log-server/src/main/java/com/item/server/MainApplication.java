package com.item.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zcm
 */
@SpringBootApplication
public class MainApplication {

    private static final int LENGTH_OF_ARRAY= 20;


    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

    public static void gest(){
        StringBuilder str = new StringBuilder("start");
        for (int i = 0; i < 10; i++) {
            str.append("hello");
        }
    }
}
