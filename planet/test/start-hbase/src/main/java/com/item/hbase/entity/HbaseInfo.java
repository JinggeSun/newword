package com.item.hbase.entity;

import java.util.List;

/**
 * @author zcm
 */
public class HbaseInfo {

    private DbInfo dbInfo;

    private List<TableInfo> tableInfos;


    public DbInfo getDbInfo() {
        return dbInfo;
    }

    public void setDbInfo(DbInfo dbInfo) {
        this.dbInfo = dbInfo;
    }

    public List<TableInfo> getTableInfos() {
        return tableInfos;
    }

    public void setTableInfos(List<TableInfo> tableInfos) {
        this.tableInfos = tableInfos;
    }

    @Override
    public String toString() {
        return "HiveInfo{" +
                "dbInfo=" + dbInfo +
                ", tableInfos=" + tableInfos +
                '}';
    }
}
