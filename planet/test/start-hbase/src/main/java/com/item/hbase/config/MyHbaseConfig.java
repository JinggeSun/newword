package com.item.hbase.config;

import com.item.hbase.properties.MyHbaseProperties;
import com.item.hbase.service.MyHbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(MyHbaseProperties.class)
@ConditionalOnProperty(
        prefix = "myhive",
        name = "isopen",
        havingValue = "true"
)
public class MyHbaseConfig {

    @Autowired
    MyHbaseProperties myHbaseProperties;

    @Bean(name = "myHbase")
    public MyHbaseService myHbase() {
        return new MyHbaseService(myHbaseProperties.getZookeeperIp(), myHbaseProperties.getZookeeperPort());
    }

}
