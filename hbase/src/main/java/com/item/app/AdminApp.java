package com.item.app;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author zcm
 */
public class AdminApp {

    private static HBaseAdmin admin;

    private static final String COLUMNS_FAMILY_1 = "cf1";
    private static final String COLUMNS_FAMILY_2 = "cf2";

    /**
     * 获取hbase的连接
     * @return
     * @throws IOException
     */
    public static Connection initHbase() throws IOException {
        //创建连接配置
        Configuration configuration = HBaseConfiguration.create();
        //zookeeper
        configuration.set("hbase.zookeeper.quorum", "127.0.0.1");
        //port
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        //工厂：连接
        return ConnectionFactory.createConnection(configuration);
    }


    /**
     * 查询表空间
     * @throws IOException
     */
    public static void listTableSpace() throws IOException {
        admin = (HBaseAdmin) initHbase().getAdmin();
        NamespaceDescriptor[] namespaceDescriptors = admin.listNamespaceDescriptors();
        Arrays.stream(namespaceDescriptors).forEach(namespaceDescriptor -> {
            System.err.println(namespaceDescriptor.getName());
        });
    }

    /**
     * 检查表是否存在
     * @param tableName
     * @throws IOException
     */
    public static void checkTable(TableName tableName) throws IOException {
        admin = (HBaseAdmin) initHbase().getAdmin();
        boolean b = admin.tableExists(tableName);
        System.err.println(b);
    }

    /**
     * 获取table列表
     * @return
     * @throws IOException
     */
    public static TableName[] getTableList() throws IOException {
        admin = (HBaseAdmin) initHbase().getAdmin();
        TableName[] tableNames = admin.listTableNames();
        System.out.println(tableNames.length);
        return tableNames;
    }


    /**
     * 获取表的列族
     * @return
     * @throws IOException
     */
    public static List<String> getFamilyDescriptor(TableName tableName) throws IOException {
        List<String> list = new ArrayList<>(3);
        admin = (HBaseAdmin) initHbase().getAdmin();
        TableDescriptor descriptor = admin.getDescriptor(tableName);
        for (ColumnFamilyDescriptor descriptor1 : descriptor.getColumnFamilies()){
            System.out.println(new String(descriptor1.getName(), StandardCharsets.UTF_8));
            list.add(new String(descriptor1.getName(), StandardCharsets.UTF_8));
            getFamilyDescriptor(descriptor1);
        }
        return list;
    }

    /**
     * 获取表的列族
     * @return
     * @throws IOException
     */
    public static void getFamilyDescriptor(ColumnFamilyDescriptor descriptor1) throws IOException {
        Map<String, String> configuration = descriptor1.getConfiguration();
        configuration.forEach((key,val)->{
            System.out.println(configuration.get(key));
        });
    }



    //创建表 create
    public static void createTable(TableName tableName, String[] cols) throws IOException {
        admin = (HBaseAdmin) initHbase().getAdmin();
        boolean flag = admin.tableExists(tableName);
        System.out.println("表，是否存在：" + flag);
        if (flag) {
            System.out.println("Table Already Exists！");
        } else {
            System.out.println("准备创建");


            //HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);


            List<ColumnFamilyDescriptor> list = new ArrayList<>();
            //列族
            for (String col : cols) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                //hTableDescriptor.addFamily(hColumnDescriptor);
                ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(col.getBytes()).build();
                list.add(columnFamilyDescriptor);
            }

            TableDescriptor tableDescriptor = TableDescriptorBuilder.newBuilder(tableName)
                    .setColumnFamilies(list)
                    .build();

            admin.createTable(tableDescriptor);
            //admin.createTable(hTableDescriptor);
            System.out.println("Table Create Successful");
        }
    }

    public static TableName getTbName(String tableName) {
        return TableName.valueOf(tableName);
    }

    // 删除表 drop
    public static void deleteTable(TableName tableName) throws IOException {
        admin = (HBaseAdmin) initHbase().getAdmin();
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

    /**
     * 查询
     * @param tableName
     * @return
     * @throws IOException
     */
    public static List<Student> allScan(TableName tableName) throws IOException {
        Table table = initHbase().getTable(tableName);

        //scan
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes("user"));
        ResultScanner results = table.getScanner(scan);

        for (Result result : results) {
            Student student = new Student();
            byte[] row = result.getRow();
            System.out.println(new String(row, StandardCharsets.UTF_8));
            for (Cell cell : result.rawCells()) {
                String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println(colName);
                System.out.println(value);
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

        listTableSpace();

//        Student student = new Student();
//        student.setId("1");
//        student.setName("Arvin");
//        student.setAge("18");

//        String table = "student";

//        getFamilyDescriptor(getTbName("qingyi"));
        //tableList();;
//        checkTable(getTbName(table));

 //       allScan(getTbName("qingyi"));
 //       createTable(getTbName(table), new String[]{COLUMNS_FAMILY_1, COLUMNS_FAMILY_2});
//        deleteTable(getTbName(table));
//        insertData(getTbName(table), student);
//        deleteData(getTbName(table), "1");
//        singleGet(getTbName(table), "2");
//        getCell(getTbName(table), "2", "cf1", "name");
    }
}
