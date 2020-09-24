package com.item.manager.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zcm
 */
@Configuration
public class EsConfig {

    @Value("${my.es.hostname}")
    String hostName;
    @Value("${my.es.port}")
    String port;
    @Value("${my.es.scheme}")
    String scheme;

    @Bean
    public RestHighLevelClient getRestHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostName, Integer.parseInt(port), scheme)));
        return client;
    }

}
