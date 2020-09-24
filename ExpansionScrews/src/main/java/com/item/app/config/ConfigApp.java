package com.item.app.config;

import cn.smallbun.screw.core.engine.EngineFileType;
import com.item.app.model.DbModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配置类
 * @author zcm
 */
public class ConfigApp {

    public static String getVersion() {
        return "2020";
    }

    private static final Map<String,DbModel> DA_SUPPORT = new LinkedHashMap<>();
    private static final Map<String, Object> EXPORT_TYPE = new LinkedHashMap<>();

    static {
        DA_SUPPORT.put("请选择",new DbModel());
        DA_SUPPORT.put("MySQL",new DbModel("com.mysql.jdbc.Driver","127.0.0.1","root","","3306","表名"));
        DA_SUPPORT.put("Oracle",new DbModel("oracle.jdbc.driver.OracleDriver","127.0.0.1","sa","","1521","表名"));
        DA_SUPPORT.put("SQL Server",new DbModel("com.microsoft.sqlserver.jdbc.SQLServerDriver","127.0.0.1","sa","","1433","表名"));

        EXPORT_TYPE.put("html",EngineFileType.HTML);
        EXPORT_TYPE.put("word",EngineFileType.WORD);
    }




    public static String[] getSupportDb(){
        String[] support = new String[DA_SUPPORT.size()];
        final int[] i = {0};
        DA_SUPPORT.keySet().forEach((key)->{
            support[i[0]] = key;
            i[0] += 1;
        });
        return support;
    }

    public static DbModel getDbModelByDb(String key){
        return DA_SUPPORT.get(key);
    }


    public static String[] getExportType(){
        String[] type = new String[EXPORT_TYPE.size()];
        final int[] i = {0};
        EXPORT_TYPE.keySet().forEach((key)->{
            type[i[0]] = key;
            i[0] += 1;
        });
        return type;
    }
}
