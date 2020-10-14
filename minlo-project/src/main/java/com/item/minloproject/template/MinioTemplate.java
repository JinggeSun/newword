package com.item.minloproject.template;

import com.item.minloproject.pojo.MinioProp;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author zcm
 */
@Component
public class MinioTemplate {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(MinioTemplate.class);
    /**
     * 目录
     */
    private static final String TMP_DIR = System.getProperty("java.io.tmpdir")+ File.separator;
    /**
     * 参数
     */
    private MinioProp minioProp;
    /**
     * minioclient参数
     */
    private MinioClient minioClient;

    /**
     * 构造函数
     */
    public MinioTemplate(MinioProp minioProp,MinioClient minioClient){
        super();
        this.minioProp = minioProp;
        this.minioClient = minioClient;
    }

    /**
     * 判断文件桶是否存在
     * @param bucketName
     * @return
     */
    public boolean bucketExists(String bucketName){
        try {
            return minioClient.bucketExists(bucketName);
        }catch (Exception e){
            logger.error("",e);
        }
        return false;
    }

    /**
     * 创建
     * @param bucketName
     * @throws ErrorResponseException
     * @throws IllegalArgumentException
     * @throws InsufficientDataException
     * @throws InternalException
     * @throws InvalidBucketNameException
     * @throws InvalidResponseException
     * @throws NoSuchAlgorithmException
     * @throws XmlParserException
     * @throws IOException
     * @throws RegionConflictException
     */
    public void makeBucket(String bucketName) throws InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException, RegionConflictException{
        boolean isExist = minioClient.bucketExists(bucketName);
        if(!isExist) {
            minioClient.makeBucket(bucketName);
        }
    }


}
