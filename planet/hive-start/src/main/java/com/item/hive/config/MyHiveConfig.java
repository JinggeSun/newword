package com.item.hive.config;

import com.item.hive.properties.MyHiveProperties;
import com.item.hive.service.MyHiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zcm
 */
@Configuration
@EnableConfigurationProperties(MyHiveProperties.class)
@ConditionalOnProperty(
        prefix = "myhive",
        name = "isopen",
        havingValue = "true"
)
public class MyHiveConfig {

    @Autowired
    MyHiveProperties myHiveProperties;

    @Bean(name = "myHive")
    public MyHiveService myHiveService() {
        return new MyHiveService(myHiveProperties.getHiveJdbcUrl(), myHiveProperties.getUsername(),
                myHiveProperties.getPassword(), myHiveProperties.getHiveThriftUrl());
    }

}
