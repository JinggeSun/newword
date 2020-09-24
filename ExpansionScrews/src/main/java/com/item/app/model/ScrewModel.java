package com.item.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrewModel {

    //数据库配置
    /**
     *
     */
    private String driverClassName;
    private String jdbcUrl;
    private String userName;
    private String password;
    private String fileOutputDir;
    private String fileType;


}
