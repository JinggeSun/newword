package com.item.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表实体
 * @author zcm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDocument {

    private String name;
    private String desc;
    private String num;
    private String columnName;
    private String type;
    private String length;
    private String dot;
    private String nullStr;
    private String key;
    private String defaultValue;
    private String comment;
}
