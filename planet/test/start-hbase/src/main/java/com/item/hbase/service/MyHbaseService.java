package com.item.hbase.service;

import com.item.hbase.entity.MyColumnsFamily;
import com.item.hbase.entity.TableInfo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hive.metastore.api.MetaException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zcm
 */
public class MyHbaseService {

    private String zookeeperIp;
    private String zookeeperPort;

    private static HBaseAdmin admin;

    public MyHbaseService(String zookeeperIp,String zookeeperPort){
        this.zookeeperIp = zookeeperIp;
        this.zookeeperPort = zookeeperPort;
    }

    /**
     * 使用client
     *
     * @throws MetaException
     * @return
     */
    private void getHbaseConnect() throws IOException {
        if (admin == null) {
            //创建连接配置
            Configuration configuration = HBaseConfiguration.create();
            //zookeeper
            configuration.set("hbase.zookeeper.quorum", zookeeperIp);
            //port
            configuration.set("hbase.zookeeper.property.clientPort", zookeeperPort);
            //工厂：连接
            Connection connection = ConnectionFactory.createConnection(configuration);

            admin = (HBaseAdmin) connection.getAdmin();
        }
    }

    /**
     * 检查表是否存在
     * @param tableName
     * @throws IOException
     */
    public boolean checkTable(TableName tableName) throws IOException {
        try {
            getHbaseConnect();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return admin.tableExists(tableName);
    }

    /**
     * 获取table列表
     * @return
     * @throws IOException
     */
    public  List<TableInfo> getTableList() {

        List<TableInfo> list = new ArrayList<>();

        try {
            getHbaseConnect();
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        }

        TableName[] tableNames = new TableName[0];
        try {
            tableNames = admin.listTableNames();
            Arrays.stream(tableNames).forEach(tableName -> {
                String name = new String(tableName.getName());
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(name);
                list.add(tableInfo);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 获取表的列族
     * @return
     * @throws IOException
     */
    public List<String> getFamilyDescriptor(String tableName) throws IOException {
        List<String> list = new ArrayList<>(3);

        try {
            getHbaseConnect();
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        }

        TableName tableTab = getTbName(tableName);
        TableDescriptor descriptor = admin.getDescriptor(tableTab);
        for (ColumnFamilyDescriptor descriptor1 : descriptor.getColumnFamilies()){
            System.out.println(new String(descriptor1.getName(), StandardCharsets.UTF_8));
            list.add(new String(descriptor1.getName(), StandardCharsets.UTF_8));
        }
        return list;
    }

    /**
     * 获取表的列族,下的详细详细
     * @return
     * @throws IOException
     */
    public List<MyColumnsFamily> getFamilyDescriptor(ColumnFamilyDescriptor descriptor1) throws IOException {
        List<MyColumnsFamily> list = new ArrayList<>();
        Map<String, String> configuration = descriptor1.getConfiguration();
        configuration.forEach((key,val)->{
            MyColumnsFamily columnsFamily = new MyColumnsFamily();
            columnsFamily.setName(configuration.get(key));
        });

        return list;
    }


    /**
     * 转换
     * @param tableName
     * @return
     */
    public TableName getTbName(String tableName) {
        return TableName.valueOf(tableName);
    }


    /**
     * 创建表
     */
    public boolean createTable(String tableNameStr, String[] cols) throws IOException {
        try {
            getHbaseConnect();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        TableName tableName = getTbName(tableNameStr);
        boolean flag = admin.tableExists(tableName);
        System.out.println("表，是否存在：" + flag);
        if (flag) {
            return false;
        } else {
            List<ColumnFamilyDescriptor> list = new ArrayList<>();
            //列族
            for (String col : cols) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(col.getBytes()).build();
                list.add(columnFamilyDescriptor);
            }
            TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(tableName)
                    .setColumnFamilies(list)
                    .build();
            admin.createTable(tableDescriptor);
            return true;
        }
    }


    /**
     * 删除表 drop
     * @param tableNameStr
     * @throws IOException
     */
    public  boolean deleteTable(String tableNameStr) throws IOException {
        TableName tableName = getTbName(tableNameStr);
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            return true;
        } else {
            return false;
        }
    }


    /**
     * put 插入数据
     * @param tableName
     * @param student
     * @throws IOException
     */
    public static void insertData(String tableName, Map<String,Object> contentList) throws IOException {
        String id = student.getId();
        Put put = new Put(Bytes.toBytes(id));
        String name = student.getName();
        put.addColumn(Bytes.toBytes(COLUMNS_FAMILY_1), Bytes.toBytes("name"), Bytes.toBytes(name));
        put.addColumn(Bytes.toBytes(COLUMNS_FAMILY_1), Bytes.toBytes("age"), Bytes.toBytes(student.getAge()));
        initHbase().getTable(tableName).put(put);
        System.out.println("Data insert success:" + student.toString());
    }

    // delete 删除数据
    public static void deleteData(TableName tableName, String rowKey) throws IOException {
        Delete delete = new Delete(Bytes.toBytes(rowKey));      // 指定rowKey
//        delete = delete.addColumn(Bytes.toBytes(COLUMNS_FAMILY_1), Bytes.toBytes("name"));  // 指定column，也可以不指定，删除该rowKey的所有column
        initHbase().getTable(tableName).delete(delete);
        System.out.println("Delete Success");
    }

}
