package com.item.app.hive;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.thrift.TException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HiveClientUtil {

    private static HiveMetaStoreClient hiveMetaStoreClient;
    private static final String HIVE_THRIFTS = "thrift://127.0.0.1:9083";


    /**
     * 初始化,后面可以改单例
     */
    static {
        try {
            HiveConf hiveConf = new HiveConf();
            hiveConf.set("hive.metastore.uris",HIVE_THRIFTS);
            hiveMetaStoreClient = new HiveMetaStoreClient(hiveConf);
            System.out.println("connection success ! ");
        } catch (MetaException e) {
            e.printStackTrace();
        }
    }

    /**
     * 所有数据库
     * @return
     */
    public static List<String> getAllDataBases(){
        List<String> resList = new ArrayList<>(3);
        try {
            resList = hiveMetaStoreClient.getAllDatabases();
        } catch (MetaException e) {
            e.printStackTrace();
        }
        return resList;
    }

    /**
     * 获取某个数据库下所有的表
     * @param dbName
     * @return
     */
    public static List<String> getTablesByDbName(String dbName){
        List<String> resList = new ArrayList<>(3);
        try {
            resList = hiveMetaStoreClient.getAllTables(dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resList;
    }

    /**
     * 获取表的字段
     * @param dbName
     * @param tableName
     * @return
     */
    public static List<FieldSchema> showSchemaByDbAndTable(String dbName, String tableName){
        List<FieldSchema> resultList = new ArrayList<>(3);

        try {
            Table table = hiveMetaStoreClient.getTable(dbName,tableName);
            resultList = table.getSd().getCols();

            if (table.isSetPartitionKeys()){
                List<FieldSchema> partitionKeys = table.getPartitionKeys();
                partitionKeys.forEach(s -> {
                    s.setComment("partition key");
                });
                resultList.addAll(partitionKeys);
            }
        } catch (TException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public static void main(String[] args) {
        //1. 获取数据库
        List<String> allDataBases = getAllDataBases();
        allDataBases.forEach(System.out::println);
    }

}
