package com.item.hive.field;

import java.util.List;

/**
 * @author zcm
 */
public class TableFieldInfo {

    private String tableName;

    private List<TableField> tableFieldList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableField> getTableFieldList() {
        return tableFieldList;
    }

    public void setTableFieldList(List<TableField> tableFieldList) {
        this.tableFieldList = tableFieldList;
    }
}
