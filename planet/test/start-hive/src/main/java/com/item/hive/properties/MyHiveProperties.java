package com.item.hive.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zcm
 */
@ConfigurationProperties(prefix = "myhive")
public class MyHiveProperties {

    private String hiveJdbcUrl;
    private String username;
    private String password;
    private String hiveThriftUrl;

    public String getHiveJdbcUrl() {
        return hiveJdbcUrl;
    }

    public void setHiveJdbcUrl(String hiveJdbcUrl) {
        this.hiveJdbcUrl = hiveJdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHiveThriftUrl() {
        return hiveThriftUrl;
    }

    public void setHiveThriftUrl(String hiveThriftUrl) {
        this.hiveThriftUrl = hiveThriftUrl;
    }
}
