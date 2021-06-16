package com.item.manager.custom;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis plus 自动填充字段。经过配置后，一些默认的字段不需要在实体里编写
 * @author sunjg
 */
@Component
@Slf4j
public class CustomizeMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时默认填充的字段
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        LocalDateTime localDate = LocalDateTime.now();
        // filedName 是实体的名字，不是数据库的名字
        this.setFieldValByName("createTime", localDate,metaObject);
//        this.setFieldValByName("updateTime",localDate,metaObject);

       // this.setFieldValByName("operator", "Jerry", metaObject);

    }

    /**
     * 更新时，默认填充的字段
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        LocalDateTime localDate = LocalDateTime.now();
        // filedName 是实体的名字，不是数据库的名字
        this.setFieldValByName("updateTime", localDate, metaObject);
    }
}
