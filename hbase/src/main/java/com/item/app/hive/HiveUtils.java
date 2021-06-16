package com.item.app.hive;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.thrift.TException;
import org.junit.After;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zcm
 */
public class HiveUtils {

    /**
     *     jdbc 4大件
     */
    private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";
    private static final String URL = "jdbc:hive2://127.0.0.1:10000/default";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * 连接的基本配置
     */
    private static HiveMetaStoreClient hiveMetaStoreClient;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * 初始化,后面可以改单例
     */
    static {
        try {
            HiveConf hiveConf = new HiveConf();
            hiveConf.set("hive.metastore.uris","thrift://127.0.0.1:9083");
            hiveMetaStoreClient = new HiveMetaStoreClient(hiveConf);
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connection.createStatement();
            System.out.println("connection success ! ");
        } catch (ClassNotFoundException | SQLException | MetaException e) {
            e.printStackTrace();
        }
    }

    public static PreparedStatement prepare(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
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
    public static List<FieldSchema> showSchemaByDbAndTable(String dbName,String tableName){
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

    /**
     * 查询分区
     * @param dbName
     * @param tbName
     * @return
     */
    public static List<String> showPartitions(String dbName,String tbName){
        List<String> pars = new ArrayList<>();
        try {
            String sql = "show partitions "+dbName+"."+tbName;
            System.out.println("Running: " + sql);
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String p1 = resultSet.getString(1);
                pars.add(p1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pars;
    }

    /**
     * 关闭
     * @throws SQLException
     */
    @After
    public void destory() throws SQLException {
        if (resultSet != null){
            resultSet.close();
        }
        if (statement != null){
            statement.close();
        }
        if (connection != null){
            connection.close();
        }
    }

    /**
     * 执行sql
     * @param sql
     * @return
     */
    public static List<Map<String,Object>> showPartitions(String sql){
        List<Map<String,Object>> pars = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Map<String,Object> map = new HashMap<>();
                String p1 = resultSet.getString(1);
                String columnName = resultSet.getMetaData().getColumnName(1);
                map.put(columnName,p1);
                pars.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pars;
    }


    /**
     * 创建数据库
     * @param dbName
     * @return
     */
    public static void createDbName(String dbName){
        try {
            Database database = new Database();
            database.setName(dbName);
            database.setDescription("DDD");
            hiveMetaStoreClient.createDatabase(database);
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据库
     * @param tbName
     * @return
     */
    public static void createTbName(String dbName,String tbName){
        try {
            Table tbl = new Table();
            tbl.setDbName(dbName);
            tbl.setTableName(tbName);
            // 字段
            hiveMetaStoreClient.createTable(tbl);
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void queryByPage(int row,int page){
        String sql = "select * from (" +
                "select row_number() over (order by xx) as rnum ,table.* from table)t " +
                "where rnum betwneen 1 to 10;\n";
    }

    public static void main(String[] args) throws SQLException {

       // createDbName("bigdata");

        List<String> allDataBases = getAllDataBases();
        System.out.println(allDataBases.toString());
//
 //       List<String> bigData = getTablesByDbName("bigdata");
 //       System.out.println(bigData.toString());


       // String sql = "create table t2(id int, name string) stored as textfile";
       // System.out.println("Running :" + sql);
       // statement.execute(sql);

        String sql = "show databases";
        //String sql = "show tables";
       // String sql = "desc formatted bigdata.dept";
        System.out.println("Running :" + sql);
        ResultSet resultSet = statement.executeQuery(sql);
        int columns=resultSet.getMetaData().getColumnCount();

        while(resultSet.next()){
            for(int i=1;i<=columns;i++)
            {
                System.out.print(resultSet.getString(i));
                System.out.print("\t\t");
            }
            System.out.println();        }



        //   String tablename="dept";
     //   getAll(tablename);
    }

    public static void getAll(String tablename)
    {
        String sql="select * from "+tablename;
        System.out.println(sql);
        try {
            PreparedStatement ps=prepare(sql);
            ResultSet rs=ps.executeQuery();
            int columns=rs.getMetaData().getColumnCount();
            while(rs.next())
            {
                for(int i=1;i<=columns;i++)
                {
                    System.out.print(rs.getString(i));
                    System.out.print("\t\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
