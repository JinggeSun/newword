package com.item.minloproject.controller;

import com.item.minloproject.pojo.MinioProp;
import com.item.minloproject.template.MinioTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcm
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    MinioTemplate minioTemplate;

    @GetMapping
    public String getIndex(String bucketName){
        try {
            System.out.println(bucketName);
            minioTemplate.makeBucket(bucketName);
        }catch (Exception e){
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

}
