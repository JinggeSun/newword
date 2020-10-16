package com.sun.myquartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zcm
 */
@Controller
public class QuartzManagerWebController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }


}
