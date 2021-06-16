package com.item.app.config;

import cn.smallbun.screw.core.engine.EngineFileType;
import com.item.app.model.DbModel;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 配置类
 * @author zcm
 */
public class ConfigApp {

    public static final String INTRODUCE = "<html><body><p align=\\\"center\\\">膨胀螺丝是一款离据库文档导出工具<br/>核心功能是基于开源工具Screw<br/>主要特点：<br/>1. 完全离线,不会记录用户任何信息;<br/>2. Screw支持主流数据库文档的导出;<br/>3. 跨平台支持;<br/></p></body></html>";

    public static String getVersion() {
        return "2020";
    }

    private static final Map<String,DbModel> DA_SUPPORT = new LinkedHashMap<>();
    private static final Map<String, Object> EXPORT_TYPE = new LinkedHashMap<>();

    static {
        DA_SUPPORT.put("请选择",new DbModel("","","","","","数据库"));
        DA_SUPPORT.put("MySQL",new DbModel("com.mysql.jdbc.Driver","127.0.0.1","root","","3306","数据库"));
        DA_SUPPORT.put("Oracle",new DbModel("oracle.jdbc.driver.OracleDriver","127.0.0.1","sys","","1521","实例名"));
        DA_SUPPORT.put("SQL Server",new DbModel("com.microsoft.sqlserver.jdbc.SQLServerDriver","127.0.0.1","sa","","1433","数据库"));

        EXPORT_TYPE.put("html",EngineFileType.HTML);
        EXPORT_TYPE.put("word",EngineFileType.WORD);
    }

    public static String getDriverByKey(String key){
        if (DA_SUPPORT.containsKey(key)){
            DbModel dbModel = DA_SUPPORT.get(key);
            return dbModel.getDriverName();
        }
        return null;
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

    public static String getJdbcUrl(String type,String ipStr,String port,String tableName){
        //mysql
        //jdbc:mysql://127.0.0.1:3306/RefuelingWuhan
        if ("MySQL".equals(type)){
            return String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8&useSSL=false\n",ipStr,port,tableName);
        }

        //oracle
        //jdbc:oracle:thin:@localhost:1521:orcl
        //tablename 代表这实例名
        if ("Oracle".equals(type)){
            return String.format("jdbc:oracle:thin:@%s:%s:%s",ipStr,port,tableName);
        }

        //sqlserver
        //jdbc:sqlserver://localhost:1433;DatabaseName=wrySelectCourse3
        if ("SQL Server".equals(type)){
            return String.format("jdbc:sqlserver://%s:%s;DatabaseName=%s",ipStr,port,tableName);
        }

        return null;
    }

    public static void main(String[] args) {
        String jdbcUrl = getJdbcUrl("MySQL", "127.0.0.1", "3389", "DD");
        System.out.println(jdbcUrl);

    }
}
