package com.item.hive.entity;

import java.util.List;

/**
 * @author zcm
 */
public class DbInfo {

    public DbInfo(String dbName) {
        this.dbName = dbName;
    }

    private String dbName;

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    @Override
    public String toString() {
        return "DbInfo{" +
                "dbName='" + dbName + '\'' +
                '}';
    }
}
