package com.item.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * @author zcm
 */
public class StreamingApp {

    private static final String CHECK_POINT_DIR = "/Users/zcm/Documents/GitHub/newword/web-screen/streaming-ss/";


    public static void main(String[] args) throws InterruptedException {

        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("networkWC");
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, Durations.seconds(5));

        JavaReceiverInputDStream<String> localhost = javaStreamingContext.socketTextStream("localhost", 9999);

        javaStreamingContext.checkpoint(CHECK_POINT_DIR);


        JavaDStream<String> stringJavaDStream = localhost.flatMap(value -> {
            String[] word = value.split(",");
            return Arrays.asList(word).iterator();
        });

        JavaPairDStream<String, Integer> stringIntegerJavaPairDStream = stringJavaDStream.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s, 1);
            }
        });

//        JavaPairDStream<String, Integer> stringIntegerJavaPairDStream1 = stringIntegerJavaPairDStream.reduceByKey(new Function2<Integer, Integer, Integer>() {
//            @Override
//            public Integer call(Integer integer, Integer integer2) throws Exception {
//                return integer + integer2;
//            }
//        });

        JavaPairDStream<String, Integer> runningCounts = stringIntegerJavaPairDStream.updateStateByKey(new Function2<List<Integer>, Optional<Integer>, Optional<Integer>>() {
            @Override
            public Optional<Integer> call(List<Integer> integerList, Optional<Integer> integerOptional) throws Exception {
                Integer newState = 0;
                // 如果oldState之前已经存在，那么这个key可能之前已经被统计过，否则说明这个key第一次出现
                if (integerOptional.isPresent())
                {
                    newState = integerOptional.get();
                }

                // 更新state
                for (Integer value : integerList)
                {
                    newState += value;
                }
                return Optional.of(newState);
            }
        });


        runningCounts.print();

        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();

    }


}
