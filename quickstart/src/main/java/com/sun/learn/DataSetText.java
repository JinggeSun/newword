package com.sun.learn;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.CsvReader;


/**
 * @ClassName DataSetText
 * @Description: TODO
 * @Author zcm
 * @Date 2020-01-03
 * @Version V1.0
 **/
public class DataSetText {

    private static final String path = "/Users/zcm/Documents/GitHub/newword/csv";

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet csvInput = env.readCsvFile(path).ignoreFirstLine()
                .pojoType(Student.class, "id", "name", "age");

        csvInput.print();

    }
}
