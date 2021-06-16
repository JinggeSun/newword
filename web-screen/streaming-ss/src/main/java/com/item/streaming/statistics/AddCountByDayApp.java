package com.item.streaming.statistics;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import scala.Tuple2;

import java.util.*;

/**
 * 根据一个文件，文件里面是登陆日志，统计每天新增的用户数
 * 倒排索引
 */
public class AddCountByDayApp {

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf()
                .setAppName("wc9")
                .setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> lines = sparkContext.parallelize(getLogData());

        JavaPairRDD<String, String> pairs = lines.mapToPair((PairFunction<String, String, String>) word -> {
            String[] arr = word.split(",");
            return new Tuple2<String, String>(arr[1], arr[0]);
        });

        JavaPairRDD<String, Iterable<String>> stringIterableJavaPairRDD = pairs.groupByKey();

        JavaPairRDD<String, String> stringStringJavaPairRDD = stringIterableJavaPairRDD.mapToPair(new PairFunction<Tuple2<String, Iterable<String>>, String, String>() {
            @Override
            public Tuple2<String, String> call(Tuple2<String, Iterable<String>> tuple2) throws Exception {
                String dd = "";

                for (String d : tuple2._2) {
                    if (dd.length() == 0) {
                        dd = d;
                    }
                    int c = dd.compareTo(d);
                    if (c > 0) {
                        dd = d;
                    }
                }

                return new Tuple2<String, String>(dd, "1");
            }
        });

        JavaPairRDD<String, String> stringStringJavaPairRDD1 = stringStringJavaPairRDD.reduceByKey(new Function2<String, String, String>() {
            @Override
            public String call(String s, String s2) throws Exception {
                return (Integer.valueOf(s) + Integer.valueOf(s2)) + "";
            }
        });

        stringStringJavaPairRDD1.foreach(new VoidFunction<Tuple2<String, String>>() {
            @Override
            public void call(Tuple2<String, String> stringStringTuple2) throws Exception {
                System.out.println(stringStringTuple2);
            }
        });


        //别忘了关闭sparkContext
        sparkContext.close();

    }

    public static List<String> getLogData(){

        List<String> list = new ArrayList<>(9);
        list.add("2017-01-01,a");
        list.add("2017-01-01,b");
        list.add("2017-01-01,c");
        list.add("2017-01-02,a");
        list.add("2017-01-02,b");
        list.add("2017-01-02,d");
        list.add("2017-01-03,b");
        list.add("2017-01-03,e");
        list.add("2017-01-03,f");
        return list;
    }

    public static List<String> getAdData() {

        String a1 = "2020-01-01 10:00:00,user1";
        String a2 = "2020-01-02 10:00:00,user2";
        String a3 = "2020-01-03 10:00:00,user3";
        String a4 = "2020-01-04 10:00:00,user4";
        String a5 = "2020-01-05 10:00:00,user5";
        String a6 = "2020-01-06 10:00:00,user6";
        String a7 = "2020-01-07 10:00:00,user7";
        String a8 = "2020-01-08 10:00:00,user8";
        String a9 = "2020-01-09 10:00:00,user9";
        String a10 = "2020-01-10 10:00:00,user10";

        String b1 = "2020-01-01 11:00:00,user1";
        String b2 = "2020-01-03 11:00:00,user3";
        String b3 = "2020-01-05 11:00:00,user4";
        String b4 = "2020-01-06 11:00:00,user5";
        String b5 = "2020-01-07 11:00:00,user8";
        String b6 = "2020-01-08 11:00:00,user10";
        String b7 = "2020-01-10 11:00:00,user1";

        List<String> list = new ArrayList<>(17);
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);
        list.add(a7);
        list.add(a8);
        list.add(a9);
        list.add(a10);

        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        list.add(b5);
        list.add(b6);
        list.add(b7);

        return list;
    }

}
