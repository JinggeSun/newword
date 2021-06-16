package com.item.server.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author zcm
 */
@RestController
@RequestMapping("/collect")
@Log4j
public class CollectController {

    @PostMapping
    public void collectData(String data){
        if (data != null && data.length() > 0){
            log.info(data);
        }
    }

    @GetMapping
    @RequestMapping("/hello")
    public void collectMyData(String msg){
        log.info(msg);
    }
}
