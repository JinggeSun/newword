package com.item.app;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zcm
 */
public class MainApp {

    private static Admin admin;

    private static final String COLUMNS_FAMILY_1 = "cf1";
    private static final String COLUMNS_FAMILY_2 = "cf2";

    public static Connection initHbase() throws IOException {
        System.out.println("参数初始化");
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "127.0.0.1");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        //configuration.set("hbase.master", "127.0.0.1:60000");
        Connection connection = ConnectionFactory.createConnection(configuration);
        System.out.println("获取连接正常");
        System.out.println(connection.toString());
        return connection;
    }

    public static void checkTable(TableName tableName) throws IOException {
        admin = initHbase().getAdmin();
        boolean b = admin.tableExists(tableName);
        System.err.println(b);
    }

    //创建表 create
    public static void createTable(TableName tableName, String[] cols) throws IOException {
        admin = initHbase().getAdmin();
        System.out.println("获取到连接");
        System.out.println("验证表");
        boolean flag = admin.tableExists(tableName);
        System.out.println("表，是否存在：" + flag);
        if (flag) {
            System.out.println("Table Already Exists！");
        } else {
            System.out.println("准备创建");
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for (String col : cols) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
            System.out.println("Table Create Successful");
        }
    }

    public static TableName getTbName(String tableName) {
        return TableName.valueOf(tableName);
    }

    // 删除表 drop
    public static void deleteTable(TableName tableName) throws IOException {
        admin = initHbase().getAdmin();
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            System.out.println("Table Delete Successful");
        } else {
            System.out.println("Table does not exist!");
        }

    }

    //put 插入数据
    public static void insertData(TableName tableName, Student student) throws IOException {
        Put put = new Put(Bytes.toBytes(student.getId()));
        put.addColumn(Bytes.toBytes(COLUMNS_FAMILY_1), Bytes.toBytes("name"), Bytes.toBytes(student.getName()));
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

    // scan数据
    public static List<Student> allScan(TableName tableName) throws IOException {
        ResultScanner results = initHbase().getTable(tableName).getScanner(new Scan().addFamily(Bytes.toBytes("cf1")));
        List<String> list = new ArrayList<>();
        for (Result result : results) {
            Student student = new Student();
            for (Cell cell : result.rawCells()) {
                String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
            }

        }
        return null;
    }

    // 根据rowkey get数据
    public static Student singleGet(TableName tableName, String rowKey) throws IOException {
        Student student = new Student();
        student.setId(rowKey);
        Get get = new Get(Bytes.toBytes(rowKey));
        if (!get.isCheckExistenceOnly()) {
            Result result = initHbase().getTable(tableName).get(get);
            for (Cell cell : result.rawCells()) {
                String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                switch (colName) {
                    case "name":
                        student.setName(value);
                        break;
                    case "age":
                        student.setAge(value);
                        break;
                    default:
                        System.out.println("unknown columns");
                }

            }

        }

        System.out.println(student.toString());
        return student;
    }

    // 查询指定Cell数据
    public static String getCell(TableName tableName, String rowKey, String cf, String column) throws IOException {
        Get get = new Get(Bytes.toBytes(rowKey));
        String rst = null;
        if (!get.isCheckExistenceOnly()) {
            get = get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(column));
            try {

                Result result = initHbase().getTable(tableName).get(get);
                byte[] resByte = result.getValue(Bytes.toBytes(cf), Bytes.toBytes(column));
                rst = Bytes.toString(resByte);
            } catch (Exception exception) {
                System.out.printf("columnFamily or column does not exists");
            }

        }
        System.out.println("Value is: " + rst);
        return rst;
    }


    public static void main(String[] args) throws IOException {
//        Student student = new Student();
//        student.setId("1");
//        student.setName("Arvin");
//        student.setAge("18");

        String table = "student";

        checkTable(getTbName(table));


//        createTable(getTbName(table), new String[]{COLUMNS_FAMILY_1, COLUMNS_FAMILY_2});
//        deleteTable(getTbName(table));
//        insertData(getTbName(table), student);
//        deleteData(getTbName(table), "1");
//        singleGet(getTbName(table), "2");
//        getCell(getTbName(table), "2", "cf1", "name");
    }
}
