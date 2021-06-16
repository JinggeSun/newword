package com.sun.mythyleaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class MythyleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(MythyleafApplication.class, args);
    }

    @GetMapping
    public String indexPage(){
        return "index";
    }
}
