package com.item.minloproject.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * pojo 接受properties里面的配置
 * @author zcm
 */
@Data
@ConfigurationProperties(prefix = "spring.minio")
public class MinioProp {

    /**
     * 端点，地址
     */
    private String endPoint;
    /**
     * key
     */
    private String accessKey;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 文件夹
     */
    private String bucketName;




}
