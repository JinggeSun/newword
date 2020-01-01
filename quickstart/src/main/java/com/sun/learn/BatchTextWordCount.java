package com.sun.learn;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * @ClassName TextWordCount
 * @Description: 批处理词频统计
 * @Author zcm
 * @Date 2020-01-01
 * @Version V1.0
 **/
public class BatchTextWordCount {

    private static final String txtPath = "/Users/zcm/Documents/GitHub/newword/text.txt";

    public static void main(String[] args) throws Exception {



        ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();

        DataSource<String> textSource =  executionEnvironment.readTextFile(txtPath);

        textSource.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                Arrays.stream(s.split(",")).forEach((w)->collector.collect(new Tuple2<>(w,1)));
            }
        }).groupBy(0).sum(1).print();

    }
}
