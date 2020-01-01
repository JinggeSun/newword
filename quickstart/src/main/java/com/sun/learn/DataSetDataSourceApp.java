package com.sun.learn;

import org.apache.flink.api.java.ExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DataSetDateSourceApp
 * @Description: TODO
 * @Author zcm
 * @Date 2020-01-01
 * @Version V1.0
 **/
public class DataSetDataSourceApp {

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        fromCollection(env);
    }

    public static void fromCollection(ExecutionEnvironment env) throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        env.fromCollection(list).print();
    }
}
