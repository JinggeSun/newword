package com.item.app.util;

import com.item.app.model.ScrewModel;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * jdbc数据库操作工具类
 * @author zcm
 */
public class DbUtil {

    /**
     * 连接测试
     * @param screwModel
     * @return
     */
    public static String connectTest(ScrewModel screwModel){
        String flag = "";
        if (screwModel == null) {
            return "数据库实体错误";
        }
        try {
            //测试连接
            DataSource dataSource = getDataSourceByJdbc(screwModel);
            if (dataSource == null){
                return "数据库连接异常！";
            }
            Connection connection = null;
            connection = dataSource.getConnection();
            if (connection == null){
                return "数据库连接异常！";
            }
            flag = "";
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return "数据库连接异常！";
        }
        return flag;
    }

    public static DataSource getDataSource(ScrewModel screwModel){
        return getDataSourceByJdbc(screwModel);
    }

    private static DataSource getDataSourceByJdbc(ScrewModel screwModel){

        DataSource dataSource = null;

        try {
            //数据源
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(screwModel.getDriverClassName());
            hikariConfig.setJdbcUrl(screwModel.getJdbcUrl());
            hikariConfig.setUsername(screwModel.getUserName());
            hikariConfig.setPassword(screwModel.getPassword());
            /**
             * 设置可以获取tables remarks信息
             * 以下配置可以继续在页面配置中展示出来
             * TODO v1
             */
            hikariConfig.addDataSourceProperty("useInformationSchema", "true");
            hikariConfig.setMinimumIdle(2);
            hikariConfig.setMaximumPoolSize(5);
            dataSource = new HikariDataSource(hikariConfig);
        }catch (Exception e){
            //e.printStackTrace();
            return null;
        }

        return dataSource;
    }
}
