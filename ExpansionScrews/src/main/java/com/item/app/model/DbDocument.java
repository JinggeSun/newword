package com.item.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.tools.jconsole.Tab;

import java.util.List;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbDocument {

    private String title;
    private String dbName;
    private String version;
    private String desc;

    private List<String> dbNameList;
    private List<TableDocument> tableDocumentList;
}
