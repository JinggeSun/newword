package com.item.streaming.statistics;



import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 广告信息，流出里，按照
 * @author zcm
 */
public class AdClickToRedisApp {

    public static void main(String[] args) throws InterruptedException {

        Logger.getLogger("org.apache.spark").setLevel(Level.ERROR);

        //10秒
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("networkWC");
        JavaStreamingContext jsc = new JavaStreamingContext(sparkConf, Durations.seconds(5));
        //日志格式，从socket获取
        //2020-01-02 10:00:00,ad01;
        //2020-01-02 10:00:00,ad02;
        //2020-01-02 10:00:00,ad03;
        JavaDStream<String> sourceDs = jsc.socketTextStream("localhost", 7897).window(Durations.minutes(1));
        // 逐条分析
        JavaPairDStream<String, Integer> sourceMap = sourceDs.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                String[] value = s.split(",");
                LocalDateTime localDateTime = LocalDateTime.parse(value[0],DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String va =  localDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                //ad01,10:00  1
                //ad01,10:01  1
                return new Tuple2<String, Integer>(value[1] + "," + va, 1);
            }
        });

        /**
         * reducy ++
         * reducybykey
         */
        JavaPairDStream<String, Integer> stringIntegerJavaPairDStream = sourceMap.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

        //(ad01,10:10,2)
        //(ad01,10:00,1)
        //(ad02,10:00,1)
        //(ad03,10:00,1)

        JavaPairDStream<String, String> javaPairDStream = stringIntegerJavaPairDStream.mapToPair(new PairFunction<Tuple2<String, Integer>, String, String>() {
            @Override
            public Tuple2<String, String> call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                String[] keyArr = stringIntegerTuple2._1.split(",");
                return new Tuple2<String, String>(keyArr[0], keyArr[1] + "," + stringIntegerTuple2._2);
            }
        });

        JavaPairDStream<String, Iterable<String>> pairDStream = javaPairDStream.groupByKey();

        pairDStream.print();

        jsc.start();
        jsc.awaitTermination();

    }
}
