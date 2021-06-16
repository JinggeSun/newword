package com.sun.mythyleaf.controller;

import com.sun.mythyleaf.dao.HomeDao;
import com.sun.mythyleaf.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    HomeDao homeDao;

    @Autowired
    IndexDao indexDao;

    @GetMapping("/")
    public String indexInfo(){
        System.out.println(homeDao.getScheduler());
        System.out.println(indexDao.getDept());
        return "success";
    }
}
