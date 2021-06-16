package com.item.hbase.entity;

import java.util.List;

/**
 * @author zcm
 */
public class TableInfo {

    private String tableName;

    private List<MyColumnsFamily> familyList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFamilyList(List<MyColumnsFamily> familyList) {
        this.familyList = familyList;
    }

    public List<MyColumnsFamily> getFamilyList() {
        return familyList;
    }

    public TableInfo(){
        super();
    }

    public TableInfo(String tableName){
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", familyList=" + familyList +
                '}';
    }
}
