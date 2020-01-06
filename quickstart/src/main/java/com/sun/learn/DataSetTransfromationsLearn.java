package com.sun.learn;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName DataSetTransfromationsLearn
 * @Description: TODO
 * @Author zcm
 * @Date 2020-01-02
 * @Version V1.0
 **/
public class DataSetTransfromationsLearn {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

//        DataSource<String> dataSource = env.fromCollection(getList());
//
//        dataSource.map(new MapFunction<String, Object>() {
//            @Override
//            public Object map(String s) throws Exception {
//                return s.toUpperCase();
//            }
//        }).print();

//        DataSource<String> dataSource = env.fromElements(getStr());
//
//        dataSource.flatMap(new FlatMapFunction<String, Object>() {
//            @Override
//            public void flatMap(String s, Collector<Object> collector) throws Exception {
//                Arrays.stream(s.split(",")).forEach((w)->collector.collect(new Tuple2<String,Integer>(w,1)));
//            }
//        }).print();

        DataSource<String> dataSource = env.fromCollection(Collections.singleton(getStr()));

       dataSource.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {

           @Override
           public void flatMap(String s, Collector<Tuple2<String,Integer>> collector) throws Exception {
               Arrays.stream(s.split(",")).forEach((w)->collector.collect(new Tuple2<String,Integer>(w,1)));
           }
       })
               .filter(new FilterFunction<Tuple2<String, Integer>>() {
           @Override
           public boolean filter(Tuple2 s) throws Exception {
               Tuple2<String,Integer> t = (Tuple2<String, Integer>) s;
               return t.f0.contains("h");
           }
       }).groupBy(0)
               .reduce(new ReduceFunction<Tuple2<String, Integer>>() {

           @Override
           public Tuple2<String, Integer> reduce(Tuple2 o, Tuple2 t1) throws Exception {
               Tuple2<String,Integer> q1 = (Tuple2<String, Integer>) o;
               Tuple2<String,Integer> q2 = (Tuple2<String, Integer>) t1;
               return new Tuple2<String,Integer>(q1.f0,q1.f1+q2.f1);
           }
       }).print();
    }


    public static String getStr(){
        return "hello,world,my,name,is,hello,world,my,hello,hello,my,hel,h,ewqh,dq,hq";
    }

    public static List<String> getList(){
        return Arrays.stream("hello,world,my,name,is,hello,world,my,hello,hello,my".split(",")).collect(Collectors.toList());
    }
}
