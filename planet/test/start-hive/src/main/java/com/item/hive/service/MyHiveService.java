package com.item.hive.service;

import com.item.hive.entity.DbInfo;
import com.item.hive.entity.HiveInfo;
import com.item.hive.entity.TableInfo;
import com.item.hive.field.TableFieldInfo;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.thrift.TException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zcm
 */

public class MyHiveService {

    private String hiveJdbcUrl;
    private String username;
    private String password;
    private String hiveThriftUrl;
    private static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";


    private static HiveMetaStoreClient hiveMetaStoreClient;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public MyHiveService(String hiveJdbcUrl, String username, String password, String hiveThriftUrl) {
        this.hiveJdbcUrl = hiveJdbcUrl;
        this.username = username;
        this.password = password;
        this.hiveThriftUrl = hiveThriftUrl;
    }


    /**
     * 使用client
     *
     * @throws MetaException
     */
    private void getHiveConnect() throws MetaException {
        if (hiveMetaStoreClient == null) {
            HiveConf hiveConf = new HiveConf();
            hiveConf.set("hive.metastore.uris", this.hiveThriftUrl);
            hiveMetaStoreClient = new HiveMetaStoreClient(hiveConf);
        }
    }

    private void getHiveJdbc() throws Exception {
        if (connection == null || statement == null) {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(hiveJdbcUrl, username, password);
            statement = connection.createStatement();
        }
    }

    /**
     * 查询所有的数据库
     *
     * @return
     */
    public List<DbInfo> getAllDataBases() {
        List<DbInfo> resList = new ArrayList<>(5);
        try {
            getHiveConnect();
        } catch (MetaException e) {
            e.printStackTrace();
            return resList;
        }
        try {
            List<String> allDatabases = hiveMetaStoreClient.getAllDatabases();
            assert allDatabases != null && allDatabases.size() > 0;
            allDatabases.forEach(s -> {
                resList.add(new DbInfo(s));
            });
        } catch (MetaException e) {
            e.printStackTrace();
        }
        return resList;
    }

    /**
     * 根据数据库查询表
     *
     * @param dbName
     * @return
     */
    public List<TableInfo> getTableListByDb(String dbName) {
        List<TableInfo> resList = new ArrayList<>(5);
        try {
            getHiveConnect();
        } catch (MetaException e) {
            e.printStackTrace();
            return resList;
        }
        try {
            List<String> allTables = hiveMetaStoreClient.getAllTables(dbName);
            assert allTables != null && allTables.size() > 0;
            allTables.forEach(s -> {
                try {
                    Table table = hiveMetaStoreClient.getTable(dbName, s);
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setTableName(table.getTableName());
                    resList.add(tableInfo);
                } catch (TException e) {
                    e.printStackTrace();
                }

            });
        } catch (MetaException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public List<HiveInfo> getHiveInfo() {
        List<HiveInfo> list = new ArrayList<>();
        try {
            getHiveConnect();
        } catch (MetaException e) {
            e.printStackTrace();
            return list;
        }
        List<DbInfo> allDataBases = getAllDataBases();
        allDataBases.forEach(dbInfo -> {
            List<TableInfo> tableListByDb = getTableListByDb(dbInfo.getDbName());
            HiveInfo hiveInfo = new HiveInfo();
            hiveInfo.setDbInfo(dbInfo);
            hiveInfo.setTableInfos(tableListByDb);
            list.add(hiveInfo);
        });
        return list;
    }


    /**
     * 创建数据库
     *
     * @param dbName
     */
    public boolean createDb(String dbName) {
        boolean flag = false;
        try {
            getHiveConnect();
        } catch (MetaException e) {
            e.printStackTrace();
            return false;
        }
        Database dbInfo = new Database();
        dbInfo.setName(dbName);
        try {
            hiveMetaStoreClient.createDatabase(dbInfo);
            flag = true;
        } catch (TException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean createTable(TableFieldInfo tableFieldInfo) {

        boolean execute = false;

        try {
            getHiveJdbc();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        final String[] sql = {" CREATE TABLE IF NOT EXISTS "};
        sql[0] += " ` ";
        sql[0] += tableFieldInfo.getTableName();
        sql[0] += " ` ( ";

        tableFieldInfo.getTableFieldList().forEach(info -> {
            sql[0] += " ` " + info.getFieldName() + " ` ";
            sql[0] += " " + info.getFieldType() + " ";
            sql[0] += " COMMENT ";
            sql[0] += " ` " + info.getComment() + " ` ,";
        });

        sql[0] = sql[0].substring(0, sql[0].length() - 1);

        sql[0] += " ) ";
        sql[0] += " partitioned by(date string) " +
                " ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' ";

        System.out.println(sql[0]);

        PreparedStatement ps = prepare(sql[0]);
        try {
            execute = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return execute;
    }


    public void querySql(String sql) {

        try {
            getHiveJdbc();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement ps = prepare(sql);
            ResultSet rs = ps.executeQuery();
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(rs.getString(i));
                    System.out.print("\t\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
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


}
